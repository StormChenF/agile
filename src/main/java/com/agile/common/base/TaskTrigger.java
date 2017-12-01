package com.agile.common.base;

import com.agile.common.annotation.Task;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronTrigger;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 佟盟 on 2017/11/29
 */
public class TaskTrigger implements Trigger, Serializable {
    private static final long serialVersionUID = -3781970872540402463L;
    private String cron;
    private boolean sync;
    public TaskTrigger(Task task){
        this.cron = task.cron();
        this.sync = task.sync();
    }

    @NotNull
    @Override
    public Date nextExecutionTime(@NotNull TriggerContext triggerContext) {
        CronTrigger cronTrigger = new CronTrigger(this.cron);
        return cronTrigger.nextExecutionTime(triggerContext);
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }
}
