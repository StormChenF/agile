package com.agile.common.base;

import com.agile.common.config.TaskConfig;
import com.agile.mvc.model.entity.SysTaskEntity;

/**
 * Created by 佟盟 on 2017/11/30
 */
public class TaskInfo extends SysTaskEntity {

    private TaskTrigger trigger; //触发器
    private TaskConfig.Job job; //任务

    public TaskInfo(SysTaskEntity sysTaskEntity) {
        this.setName(sysTaskEntity.getName());
        this.setLockName(sysTaskEntity.getLockName());
        this.setCron(sysTaskEntity.getCron());
        this.setState(sysTaskEntity.getState());
        this.setCreateTime(sysTaskEntity.getCreateTime());
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
