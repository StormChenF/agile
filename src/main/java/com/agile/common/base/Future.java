package com.agile.common.base;

import java.util.concurrent.ScheduledFuture;

/**
 * Created by 佟盟 on 2017/11/29
 */
public class Future {
    private String cron;
    private ScheduledFuture<?> future;
    public Future(String cron, ScheduledFuture<?> future) {
        this.cron = cron;
        this.future = future;
    }
    public String getCron() {
        return cron;
    }
    public void setCron(String cron) {
        this.cron = cron;
    }
    public java.util.concurrent.ScheduledFuture<?> getFuture() {
        return future;
    }
    public void setFuture(java.util.concurrent.ScheduledFuture<?> future) {
        this.future = future;
    }
}
