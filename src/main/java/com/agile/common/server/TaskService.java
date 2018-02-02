package com.agile.common.server;

import com.agile.common.annotation.Init;
import com.agile.common.base.TaskInfo;
import com.agile.common.base.TaskTrigger;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import com.agile.mvc.model.dao.Dao;
import com.agile.mvc.model.entity.SysTaskEntity;
import com.agile.mvc.model.entity.SysTaskTargetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by 佟盟 on 2018/2/2
 */
@Component
public class TaskService {
    private final Dao dao;
    private final RedisService redisService;
    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private static Map<String, TaskInfo> taskInfoMap = new HashMap<>();

    @Autowired
    public TaskService(Dao dao, RedisService redisService, ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        this.dao = dao;
        this.redisService = redisService;
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }

    /**
     * 任务对象
     */
    public class Job implements Serializable, Runnable {
        private static final long serialVersionUID = 1352043270981352844L;

        private String taskName;
        private TaskTrigger trigger;
        private List<SysTaskTargetEntity> sysTaskTargetEntityList;

        Job(String taskName, TaskTrigger trigger, List<SysTaskTargetEntity> sysTaskTargetEntityList) {
            this.taskName = taskName;
            this.trigger = trigger;
            this.sysTaskTargetEntityList = sysTaskTargetEntityList;
        }

        @Override
        public void run() {
            synchronized(this) {
                //判断是否需要同步，同步情况下获取同步锁后方可执行，非同步情况下直接运行
                if (this.trigger.isSync()) {
                    //获取下次执行时间（秒）
                    long nextTime = (this.trigger.nextExecutionTime(new SimpleTriggerContext()).getTime() - new Date().getTime()) / 1000;

                    //如果抢到同步锁，设置锁定时间并直接运行
                    if (setNxLock(this.taskName, (int) nextTime)) {
                        invoke(sysTaskTargetEntityList);
                    }
                }else{
                    invoke(sysTaskTargetEntityList);
                }
            }
        }
    }

    /**
     * 逐个执行定时任务目标方法
     * @param sysTaskTargetEntityList 定时任务详情数据集
     */
    private void invoke(List<SysTaskTargetEntity> sysTaskTargetEntityList){
        try {
            //逐个执行定时任务目标方法
            for(int i=0;i<sysTaskTargetEntityList.size();i++){
                SysTaskTargetEntity sysTaskTargetEntity = sysTaskTargetEntityList.get(i);
                if(ObjectUtil.isEmpty(sysTaskTargetEntity))return;
                String className = sysTaskTargetEntity.getTargetPackage() + "." + sysTaskTargetEntity.getTargetClass();
                Class<?> clazz = Class.forName(className);
                Object targetBaen = FactoryUtil.getBean(clazz);
                Method taretMethod = clazz.getDeclaredMethod(sysTaskTargetEntity.getTargetMethod());
                taretMethod.setAccessible(true);
                taretMethod.invoke(targetBaen);
            }
        } catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * spring容器初始化时初始化全部定时任务
     */
    @Init
    private void init(){
        //获取持久层定时任务数据集
        List<SysTaskEntity> list = dao.findAll(SysTaskEntity.class);
        for (int i = 0 ; i < list.size();i++ ) {
            SysTaskEntity sysTaskEntity = list.get(i);
            addTask(sysTaskEntity);
        }
    }

    /**
     * 根据定时任务对象添加定时任务
     * @return 是否添加成功
     */
    private boolean addTask(SysTaskEntity sysTaskEntity){
        try {
            //获取定时任务详情列表
            List<SysTaskTargetEntity> sysTaskTargetEntityList = dao.findAll("select a.* from sys_task_target a left join sys_bt_task_target b on b.sys_task_target_id = a.sys_task_target_id where b.sys_task_id = ? order by b.order", SysTaskTargetEntity.class, sysTaskEntity.getSysTaskId());

            if(ObjectUtil.isEmpty(sysTaskTargetEntityList)){
                return false;
            }

            //新建定时任务触发器
            TaskTrigger trigger = new TaskTrigger(sysTaskEntity.getCron(),sysTaskEntity.getSync());

            //新建任务
            TaskService.Job job = new TaskService.Job(sysTaskEntity.getName(), trigger, sysTaskTargetEntityList);

            ScheduledFuture scheduledFuture = null;
            if(sysTaskEntity.getState()){
                scheduledFuture = threadPoolTaskScheduler.schedule(job, trigger);
            }

            //定时任务装入缓冲区
            taskInfoMap.put(sysTaskEntity.getSysTaskId().toString(), new TaskInfo(sysTaskEntity,trigger,job,scheduledFuture));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 删除定时任务
     * @param id sysTaskEntity主键
     * @return 是否成功
     */
    private boolean removeTask(String id){
        if(taskInfoMap.containsKey(id)){
            if(!stopTask(id))return false;
            taskInfoMap.remove(id);
        }
        return true;
    }

    /**
     * 停止定时任务
     * @param id sysTaskEntity主键
     * @return 是否成功
     */
    private boolean stopTask(String id){
        try {
            TaskInfo taskInfo = taskInfoMap.get(id);
            if(ObjectUtil.isEmpty(taskInfo))return true;
            ScheduledFuture future = taskInfo.getScheduledFuture();
            if(ObjectUtil.isEmpty(future))return true;
            future.cancel(Boolean.TRUE);
        }catch (Exception e){
            return false;
        }
        return true;
    }


    /**
     * 开启定时任务
     * @param id sysTaskEntity主键
     * @return 是否成功
     */
    private boolean startTask(String id){
        try {
            TaskInfo taskInfo = taskInfoMap.get(id);
            if(ObjectUtil.isEmpty(taskInfo))return false;
            ScheduledFuture future = threadPoolTaskScheduler.schedule(taskInfo.getJob(), taskInfo.getTrigger());
            taskInfo.setScheduledFuture(future);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 更新定时任务
     * @param sysTaskEntity 定时任务对象
     * @return 是否成功
     */
    private boolean updateTask(SysTaskEntity sysTaskEntity){
        try {
            if(!removeTask(sysTaskEntity.getSysTaskId().toString()))return false;
            return addTask(sysTaskEntity);
        }catch (Exception e){
            return false;
        }
    }


    /**
     * 获取分布式锁
     *
     * @param lockName 锁名称
     * @param second   加锁时间（秒）
     * @return 如果获取到锁，则返回lockKey值，否则为null
     */
    private boolean setNxLock(String lockName, int second) {
        synchronized(this) {
            //生成随机的Value值
            String lockKey = UUID.randomUUID().toString();
            //抢占锁
            Long lock = redisService.setNx(lockName, lockKey);
            if (lock == 1) {
                //拿到Lock，设置超时时间
                redisService.expire(lockName, second - 1);
            }
            return lock == 1;
        }
    }

}
