package com.agile.common.base;

import com.agile.common.config.TaskConfig;
import com.agile.mvc.model.entity.SysTaskEntity;

/**
 * Created by 佟盟 on 2017/11/30
 */
public class TaskInfo extends SysTaskEntity {

    private static final long serialVersionUID = 9011368858242396036L;
    private TaskTrigger trigger; //触发器
    private TaskConfig.Job job; //任务

    public TaskInfo(SysTaskEntity sysTaskEntity,TaskTrigger trigger,TaskConfig.Job job) {
        this.setName(sysTaskEntity.getName());
        this.setCron(sysTaskEntity.getCron());
        this.setState(sysTaskEntity.getState());
        this.setCreateTime(sysTaskEntity.getCreateTime());
        this.trigger = trigger;
        this.job = job;
    }

    public TaskTrigger getTrigger() {
        return trigger;
    }

    public void setTrigger(TaskTrigger trigger) {
        this.trigger = trigger;
    }

    public TaskConfig.Job getJob() {
        return job;
    }

    public void setJob(TaskConfig.Job job) {
        this.job = job;
    }
}
