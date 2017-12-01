package com.agile.common.util;

import com.agile.common.base.Future;
import com.agile.common.base.TaskInfo;
import com.agile.common.config.TaskConfig;
import com.agile.mvc.model.entity.SysTaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by 佟盟 on 2017/11/29
 */
@Component
public class TaskUtil {
    @Autowired
    private TaskConfig taskConfig;
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private Map<String, Future> futureMap = new HashMap<>();
    @Bean
    public ThreadPoolTaskScheduler TaskThreadPool() {
        return new ThreadPoolTaskScheduler();
    }

    /**
     * 创建新定时器
     * @param sysTaskEntity SysTaskEntity
     * @return String
     */
    public boolean startTask(SysTaskEntity sysTaskEntity) {
        String lockName = sysTaskEntity.getLockName();
        if (ObjectUtil.isEmpty(futureMap.get(lockName))) {
            TaskInfo taskInfo = taskConfig.getSysTaskInfoMap().get(lockName);
            if(taskInfo != null) {
                String cron = taskInfo.getTrigger().getCron();
                ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(taskInfo.getJob(), new CronTrigger(cron));
                futureMap.put(taskInfo.getLockName(), new Future(cron, future));
            }
            return true;
        }
        return false;
    }
    /**
     * 修改定时器
     * @param sysTaskEntity KyTaskDetails
     * @return String
     */
    public boolean changeTask(SysTaskEntity sysTaskEntity) {
        String lockName = sysTaskEntity.getLockName();
        String cron = sysTaskEntity.getCron();
        if (stopTask(lockName)) {
            TaskInfo taskInfo = taskConfig.getSysTaskInfoMap().get(lockName);
            taskInfo.getTrigger().setCron(cron);
            ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(taskInfo.getJob(), new CronTrigger(taskInfo.getTrigger().getCron()));
            futureMap.put(taskInfo.getLockName(), new Future(cron, future));
            return true;
        }
        return false;
    }
    /**
     * 关闭定时器
     * @param lockName String
     * @return String
     */
    public boolean stopTask(String lockName) {
        Future future = futureMap.get(lockName);
        if (future != null) {
            future.getFuture().cancel(Boolean.TRUE);
            futureMap.remove(lockName);
            return true;
        }
        return false;
    }
    public Map<String, Future> getFutureMap() {
        return futureMap;
    }
}
