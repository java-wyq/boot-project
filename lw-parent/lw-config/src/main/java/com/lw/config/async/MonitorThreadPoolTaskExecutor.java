package com.lw.config.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @author wangyanqiang
 * @title: MonitorThreadPoolTaskExecutor
 * @date 2020/4/2910:09
 */
public class MonitorThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
    private static final Logger logger = LoggerFactory.getLogger(MonitorThreadPoolTaskExecutor.class);

    private void showMonitorInfo(String prefix) {
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();
        if (null == threadPoolExecutor) {
            return;
        }

        logger.info("Thread-prefix：{},monitor-prefix： {},task-count： [{}], over-task-count： [{}], active-thread-count： [{}], queue-size： [{}]",
                this.getThreadNamePrefix(),
                prefix,
                threadPoolExecutor.getTaskCount(),
                threadPoolExecutor.getCompletedTaskCount(),
                threadPoolExecutor.getActiveCount(),
                threadPoolExecutor.getQueue().size());
    }

    @Override
    public void execute(Runnable task) {
        showMonitorInfo("execute Runnable task");
        super.execute(task);
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        showMonitorInfo("execute Runnable task with startTimeout");
        super.execute(task, startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        showMonitorInfo("submit Runnable task");
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        showMonitorInfo("submit Callable task");
        return super.submit(task);
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        showMonitorInfo("submitListenable Runnable task");
        return super.submitListenable(task);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        showMonitorInfo("submitListenable Callable task");
        return super.submitListenable(task);
    }
}
