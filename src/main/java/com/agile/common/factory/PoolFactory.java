package com.agile.common.factory;

import com.agile.common.base.ThreadPool;

import java.util.concurrent.*;

/**
 * Created by 佟盟 on 2017/11/24
 */
public class PoolFactory {
    public static ThreadPool pool() {
        return new ThreadPool();
    }
    public static ThreadPool pool(int count) {
        return new ThreadPool(count);
    }
    public static ThreadPool pool(int max, int min, int def, int step, int init) {
        return new ThreadPool(max, min, def, step, init);
    }

    /**
     * JDK线程池
     * @param corePoolSize 线程池维护线程的最少数量
     * @param maximumPoolSize 线程池维护线程的最大数量
     * @param keepAliveTime 线程池维护线程所允许的空闲时间
     * @param unit 线程池维护线程所允许的空闲时间的单位
     * @param workQueue 线程池所使用的缓冲队列
     * @param threadFactory 执行器创建新线程时要使用的工厂
     * @param handler 线程池对拒绝任务的处理策略
     */
    public static ThreadPoolExecutor pool(int corePoolSize,
                                          int maximumPoolSize,
                                          long keepAliveTime,
                                          TimeUnit unit,
                                          BlockingQueue<Runnable> workQueue,
                                          ThreadFactory threadFactory,
                                          RejectedExecutionHandler handler) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory,handler);
    }

    /**
     * JDK线程池
     * @param corePoolSize 线程池维护线程的最少数量
     * @param maximumPoolSize 线程池维护线程的最大数量
     * @param keepAliveTime 线程池维护线程所允许的空闲时间
     * @param unit 线程池维护线程所允许的空闲时间的单位
     * @param workQueue 线程池所使用的缓冲队列
     */
    public static ThreadPoolExecutor pool(int corePoolSize,
                                          int maximumPoolSize,
                                          long keepAliveTime,
                                          TimeUnit unit,
                                          BlockingQueue<Runnable> workQueue){
        return new  ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    /**
     * JDK线程池
     * @param corePoolSize 线程池维护线程的最少数量
     * @param maximumPoolSize 线程池维护线程的最大数量
     * @param keepAliveTime 线程池维护线程所允许的空闲时间
     * @param unit 线程池维护线程所允许的空闲时间的单位
     * @param workQueue 线程池所使用的缓冲队列
     * @param threadFactory 执行器创建新线程时要使用的工厂
     */
    public static ThreadPoolExecutor pool(int corePoolSize,
                                          int maximumPoolSize,
                                          long keepAliveTime,
                                          TimeUnit unit,
                                          BlockingQueue<Runnable> workQueue,
                                          ThreadFactory threadFactory){
        return new  ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,threadFactory);
    }

    /**
     * JDK线程池
     * @param corePoolSize 线程池维护线程的最少数量
     * @param maximumPoolSize 线程池维护线程的最大数量
     * @param keepAliveTime 线程池维护线程所允许的空闲时间
     * @param unit 线程池维护线程所允许的空闲时间的单位
     * @param workQueue 线程池所使用的缓冲队列
     * @param handler 线程池对拒绝任务的处理策略
     */
    public static ThreadPoolExecutor pool(int corePoolSize,
                                          int maximumPoolSize,
                                          long keepAliveTime,
                                          TimeUnit unit,
                                          BlockingQueue<Runnable> workQueue,
                                          RejectedExecutionHandler handler){
        return new  ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,handler);
    }
}