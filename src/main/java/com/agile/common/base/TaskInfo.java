package com.agile.common.base;

import com.agile.common.server.TaskService;
import com.agile.mvc.model.entity.SysTaskEntity;

import java.util.concurrent.ScheduledFuture;

/**
 * Created by 佟盟 on 2017/11/30
 */
public class TaskInfo extends SysTaskEntity {

    private static final long serialVersionUID = 9011368858242396036L;
    private TaskTrigger trigger; //触发器
    private TaskService.Job job; //任务
    private ScheduledFuture scheduledFuture;

    public TaskInfo(SysTaskEntity sysTaskEntity, TaskTrigger trigger, TaskService.Job job, ScheduledFuture scheduledFuture) {
        this.setName(sysTaskEntity.getName());
        this.setCron(sysTaskEntity.getCron());
        this.setState(sysTaskEntity.getState());
        this.setCreateTime(sysTaskEntity.getCreateTime());
        this.trigger = trigger;
        this.job = job;
        this.scheduledFuture = scheduledFuture;
    }

    public TaskTrigger getTrigger() {
        return trigger;
    }

    public void setTrigger(TaskTrigger trigger) {
        this.trigger = trigger;
    }

    public TaskService.Job getJob() {
        return job;
    }

    public void setJob(TaskService.Job job) {
        this.job = job;
    }

    public ScheduledFuture getScheduledFuture() {
        return scheduledFuture;
    }

    public void setScheduledFuture(ScheduledFuture scheduledFuture) {
        this.scheduledFuture = scheduledFuture;
    }
}
