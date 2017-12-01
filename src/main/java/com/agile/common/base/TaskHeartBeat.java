package com.agile.common.base;

import com.agile.common.annotation.Task;
import com.agile.common.util.StringUtil;
import com.agile.common.util.TaskUtil;
import com.agile.mvc.model.dao.Dao;
import com.agile.mvc.model.entity.SysTaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by 佟盟 on 2017/11/30
 */
@Component
public class TaskHeartBeat {
    @Autowired
    private TaskUtil taskUtil;
    @Autowired
    private Dao dao;
    @Task(cron = "0/5 * * * * ?", name = "heartbeatCheck", sync = false)
    public void heartbeatCheck() {
        Map<String, Future> futureMap = taskUtil.getFutureMap();
        List<SysTaskEntity> sysTaskEntityList = dao.findAll("select * from sys_task",SysTaskEntity.class);

        for (SysTaskEntity sysTaskEntity : sysTaskEntityList) {
            //1、判断任务状态
            Future future = futureMap.get(sysTaskEntity.getLockName());
            if (future == null && sysTaskEntity.getState()) {
                //新增
                taskUtil.startTask(sysTaskEntity);
            } else {
                if (!sysTaskEntity.getState()) {
                    taskUtil.stopTask(sysTaskEntity.getLockName());
                } else {
                    String cron = sysTaskEntity.getCron();
                    if (!StringUtil.isEmpty(cron) && !cron.equals(future.getCron())) {
                        taskUtil.changeTask(sysTaskEntity);
                    }
                }
            }
        }
        //4、关闭定时器
        for (String beanName : futureMap.keySet()) {
            boolean isDel = true;
            for (SysTaskEntity task : sysTaskEntityList) {
                if (task.getLockName().equals(beanName)) {
                    isDel = false;
                    break;
                }
            }
            if (isDel) {
                taskUtil.stopTask(beanName);
            }
        }
        //System.out.println(new Date() + ", 心跳检测....  ");
    }
}
