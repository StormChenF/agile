package com.agile.common.config;

import com.agile.common.annotation.Task;
import com.agile.common.base.TaskInfo;
import com.agile.common.base.TaskTrigger;
import com.agile.common.server.RedisService;
import com.agile.common.util.FactoryUtil;
import com.agile.common.util.ObjectUtil;
import com.agile.common.util.StringUtil;
import com.agile.mvc.model.dao.Dao;
import com.agile.mvc.model.entity.SysTaskEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by 佟盟 on 2017/11/30
 */
@Component
public class TaskConfig implements BeanPostProcessor, SchedulingConfigurer {
    @Autowired
    private Dao dao;
    @Autowired
    private RedisService redisService;

    private Map<String, TaskInfo> sysTaskInfoMap = new HashMap<>();
    //记录任务集合
    private List<TaskTargetInfo> TaskInfoList = new ArrayList< >();

    public Map <String, TaskInfo> getSysTaskInfoMap() {
        return sysTaskInfoMap;
    }

    public class TaskTargetInfo {
        private Task task;
        private Method method;
        private String beanName;

        Task getTask() {
            return task;
        }
        void setTask(Task kyScheduled) {
            this.task = kyScheduled;
        }
        public Method getMethod() {
            return method;
        }
        public void setMethod(Method method) {
            this.method = method;
        }
        public String getBeanName() {
            return beanName;
        }

        public void setBeanName(String beanName) {
            this.beanName = beanName;
        }
    }
    /**
     * 任务对象
     */
    public class Job implements Serializable, Runnable {
        private static final long serialVersionUID = 1352043270981352844L;
        private Method method;
        private String taskName;
        private Object bean;
        private TaskTrigger trigger;

        Job(String taskName,Method method, TaskTrigger trigger) {
            this.trigger = trigger;
            this.method = method;
            this.bean = FactoryUtil.getBean(method.getDeclaringClass());
            this.taskName = taskName;
        }

        @Override
        public void run() {
            synchronized(this) {
                if (this.trigger.isSync()) {
                    //获取下次执行时间（秒）
                    long nextTime = (this.trigger.nextExecutionTime(new SimpleTriggerContext()).getTime() - new Date().getTime()) / 1000;

                    //如果抢到同步锁，设置锁定时间并直接运行
                    if (setNxLock(this.taskName, (int) nextTime)) {
                        try {
                            //执行自定义注解的方法
                            this.method.invoke(this.bean);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    try {
                        this.method.invoke(this.bean);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    /**
     * 配置定时器
     *
     * @param taskRegistrar ScheduledTaskRegistrar
     */
    @Override
    @Transactional
    public void configureTasks(@NotNull ScheduledTaskRegistrar taskRegistrar) {
        for (TaskTargetInfo taskTargetInfo: TaskInfoList) {
            Method method = taskTargetInfo.getMethod();
            String taskName = taskTargetInfo.getBeanName() + "." + method.getName();

            //创建触发器
            TaskTrigger trigger = new TaskTrigger(taskTargetInfo.getTask());
            //创建任务线程
            Job job = new Job(taskName,method, trigger);
            //自动将心跳任务加入调度器中
            if ("heartbeatCheck".equals(taskTargetInfo.getTask().name())) {
                taskRegistrar.addTriggerTask(job, trigger);
            } else {
                //创建任务对象
                SysTaskEntity sysTaskEntity = dao.findOne("select * from sys_task where lock_name = ?",SysTaskEntity.class,taskName);
                if(ObjectUtil.isEmpty(sysTaskEntity))sysTaskEntity = new SysTaskEntity();
                sysTaskEntity.setName(StringUtil.isEmpty(taskTargetInfo.getTask().name()) ? method.getName():taskTargetInfo.getTask().name());
                sysTaskEntity.setLockName(taskName);
                sysTaskEntity.setCreateTime(new Date());
                sysTaskEntity.setCron(trigger.getCron());
                dao.update(sysTaskEntity);

                TaskInfo taskInfo = new TaskInfo(sysTaskEntity);
                taskInfo.setTrigger(trigger);
                taskInfo.setJob(job);
                sysTaskInfoMap.put(sysTaskEntity.getLockName(), taskInfo);
            }
        }
    }

    /**
     * 获取所有自定义注解，并记录注解和方法的信息
     *
     * @param bean bean
     * @param beanName beanName
     * @return Object
     * @throws BeansException BeansException
     */
    @NotNull
    @Override
    public Object postProcessAfterInitialization(@NotNull Object bean, String beanName) throws BeansException {
        Method[] methods = ReflectionUtils.getUniqueDeclaredMethods(bean.getClass());
        for (Method method: methods) {
            Task annotation = AnnotationUtils.findAnnotation(method, Task.class);
            if(!ObjectUtil.isEmpty(annotation)){
                TaskTargetInfo taskInfo = new TaskTargetInfo();
                taskInfo.setTask(annotation);
                taskInfo.setMethod(method);
                taskInfo.setBeanName(bean.getClass().getPackage().getName()+beanName);
                TaskInfoList.add(taskInfo);
            }
        }
        return bean;
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
