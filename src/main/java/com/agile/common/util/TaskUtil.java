package com.agile.common.util;

import com.agile.common.base.Future;
import com.agile.common.base.TaskInfo;
import com.agile.common.config.TaskConfig;
import com.agile.mvc.model.dao.Dao;
import com.agile.mvc.model.entity.SysTaskEntity;
import com.agile.mvc.model.entity.SysTaskTargetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * 定时任务工具类
 * Created by 佟盟 on 2017/11/29
 */
@Component
public class TaskUtil {
    private static TaskUtil taskUtil;
    @Autowired
    private TaskConfig taskConfig;
    @Autowired
    Dao dao;
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private Map<String, Future> futureMap = new HashMap<>();
    @PostConstruct
    void init(){
        taskUtil = this;
    }
    /**
     * 开启定时器
     * @param id id
     * @return 开启状态
     */
    public static boolean startTask(String id) {

        //判断是否已经运行
        if (!ObjectUtil.isEmpty(taskUtil.futureMap.get(id))) return true;

        //获取定时任务配置工厂中该定时任务的缓存数据
        TaskInfo taskInfo = taskUtil.taskConfig.getTaskInfoMap().get(id);
        if(ObjectUtil.isEmpty(taskInfo)) return false;

        //配置并运行定时任务
        String cron = taskInfo.getTrigger().getCron();
        ScheduledFuture<?> future = taskUtil.threadPoolTaskScheduler.schedule(taskInfo.getJob(), new CronTrigger(cron));

        //放入缓存
        taskUtil.futureMap.put(id, new Future(cron, future));
        return true;
    }

    /**
     * 修改定时器
     * @param id id
     * @return String
     */
    public static boolean changeTask(String id) {
        //停止该定时任务
        if (stopTask(id)) {
            taskUtil.futureMap.remove(id);
            return startTask(id);
        }
        return false;
    }

    /**
     * 关闭定时器
     * @param id id
     * @return String
     */
    public static boolean stopTask(String id) {
        Future future = taskUtil.futureMap.get(id);
        if(ObjectUtil.isEmpty(future))return true;
        future.getFuture().cancel(Boolean.TRUE);
        taskUtil.futureMap.remove(id);
        return true;
    }

    /**
     * 新增定时任务
     */
    public static boolean update(SysTaskEntity sysTaskEntity){
        if(!sysTaskEntity.getState()){
            stopTask(sysTaskEntity.getSysTaskId().toString());
        };
        //获取定时任务详情列表
        List<SysTaskTargetEntity> sysTaskTargetEntityList = taskUtil.dao.findAll("select a.* from sys_task_target a left join sys_bt_task_target b on b.sys_task_target_id = a.sys_task_target_id where b.sys_task_id = ? order by b.order", SysTaskTargetEntity.class, sysTaskEntity.getSysTaskId());

        if(ObjectUtil.isEmpty(sysTaskTargetEntityList)){
            return false;
        }

        taskUtil.taskConfig.addConfigureTasks(sysTaskEntity,sysTaskTargetEntityList);
        return changeTask(sysTaskEntity.getSysTaskId().toString());

    }
}
