package com.agile.common.config;

import com.agile.common.base.TaskInfo;
import com.agile.common.base.TaskTrigger;
import com.agile.common.server.RedisService;
import com.agile.common.util.CollectionsUtil;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import com.agile.mvc.model.dao.Dao;
import com.agile.mvc.model.entity.SysTaskDetailEntity;
import com.agile.mvc.model.entity.SysTaskEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by 佟盟 on 2017/11/30
 */
@Component
public class TaskConfig implements SchedulingConfigurer {
    @Autowired
    private Dao dao;
    @Autowired
    private RedisService redisService;
    @Bean
    ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        return new ThreadPoolTaskScheduler();
    }

    private Map<String, TaskInfo> taskInfoMap = new HashMap<>();

    public Map <String, TaskInfo> getTaskInfoMap() {
        return taskInfoMap;
    }

    /**
     * 任务对象
     */
    public class Job implements Serializable, Runnable {
        private static final long serialVersionUID = 1352043270981352844L;

        private String taskName;
        private TaskTrigger trigger;
        private List<SysTaskDetailEntity> sysTaskDetailEntityList;

        Job(String taskName, TaskTrigger trigger, List<SysTaskDetailEntity> sysTaskDetailEntityList) {
            this.taskName = taskName;
            this.trigger = trigger;
            this.sysTaskDetailEntityList = sysTaskDetailEntityList;
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
                        invoke(sysTaskDetailEntityList);
                    }
                }else{
                    invoke(sysTaskDetailEntityList);
                }
            }
        }
    }

    /**
     * 逐个执行定时任务目标方法
     * @param sysTaskDetailEntityList 定时任务详情数据集
     */
    private void invoke(List<SysTaskDetailEntity> sysTaskDetailEntityList){
        try {
            CollectionsUtil.sort(sysTaskDetailEntityList,"order");
            //逐个执行定时任务目标方法
            for(int i=0;i<sysTaskDetailEntityList.size();i++){
                SysTaskDetailEntity sysTaskDetailEntity = sysTaskDetailEntityList.get(i);
                String className = sysTaskDetailEntity.getTargetPackage() + "." + sysTaskDetailEntity.getTargetClass();
                Class<?> clazz = Class.forName(className);
                Object targetBaen = FactoryUtil.getBean(clazz);
                Method taretMethod = clazz.getDeclaredMethod(sysTaskDetailEntity.getTargetMethod());
                taretMethod.invoke(targetBaen);
            }
        } catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 配置定时器
     * @param taskRegistrar ScheduledTaskRegistrar
     */
    @Override
    @Transactional
    public void configureTasks(@NotNull ScheduledTaskRegistrar taskRegistrar) {

        //获取持久层定时任务数据集
        List<SysTaskEntity> list = dao.findAll("select * from sys_task", SysTaskEntity.class);

        for (int i = 0 ; i < list.size();i++ ) {
            SysTaskEntity sysTaskEntity = list.get(i);

            //获取定时任务详情列表
            List<SysTaskDetailEntity> sysTaskDetailEntityList = dao.findAll("select * from sys_task_detail where sys_task_id = ?", SysTaskDetailEntity.class, sysTaskEntity.getSysTaskId());

            if(ObjectUtil.isEmpty(sysTaskDetailEntityList)){
                continue;
            }

            //新建定时任务触发器
            TaskTrigger trigger = new TaskTrigger(sysTaskEntity.getCron(),sysTaskEntity.getSync());

            //新建任务
            Job job = new Job(sysTaskEntity.getName(), trigger, sysTaskDetailEntityList);

            //定时任务装入缓冲区
            taskInfoMap.put(sysTaskEntity.getSysTaskId().toString(), new TaskInfo(sysTaskEntity,trigger,job));

            if(sysTaskEntity.getState()){
                taskRegistrar.addTriggerTask(job,trigger);
            }
        }
    }

    /**
     * 添加定时任务配置
     */
    public void addConfigureTasks(SysTaskEntity sysTaskEntity,List<SysTaskDetailEntity> sysTaskDetailEntityList){
        if(ObjectUtil.isEmpty(sysTaskDetailEntityList)){
            return;
        }
        //新建定时任务触发器
        TaskTrigger trigger = new TaskTrigger(sysTaskEntity.getCron(),sysTaskEntity.getSync());

        //新建任务
        Job job = new Job(sysTaskEntity.getName(), trigger, sysTaskDetailEntityList);

        //定时任务装入缓冲区
        taskInfoMap.put(sysTaskEntity.getName(), new TaskInfo(sysTaskEntity,trigger,job));
    }

    /**
     * 删除定时任务配置
     */
    public void removeConfigureTasks(String taskName){
        if(taskInfoMap.containsKey(taskName))
        taskInfoMap.remove(taskName);
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
