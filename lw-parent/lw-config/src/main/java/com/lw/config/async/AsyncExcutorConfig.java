package com.lw.config.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wangyanqiang
 * @title: ExcutorConfig
 * @date 2020/4/299:36
 */

@Configuration
@EnableAsync
public class AsyncExcutorConfig {
    private static final Logger logger = LoggerFactory.getLogger(AsyncExcutorConfig.class);
    @Value("${thread.corePoolSize}")
    private int corePoolSize;
    @Value("${thread.maxPoolSize}")
    private int maxPoolSize;
    @Value("${thread.queueCapacity}")
    private int queueCapacity;
    @Value("${thread.name}")
    private String threadName;

    @Bean
    public Executor asyncServiceExecutor(){
        //使用spring提供的线程类进行操作
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(corePoolSize);
        //配置最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        //配置队列大小
        executor.setQueueCapacity(queueCapacity);
        //配置线程池中线程前缀（一般根据业务不同可自定义区别对待）
        executor.setThreadNamePrefix(threadName);
        //线程池已满之后的拒绝策略，由执行该executor的调用线程来执行被拒绝的任务，也就是说谁调异步方法了谁的线程调用被拒绝的任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //初始化执行器
        executor.initialize();
        logger.info("initialized asyncServiceExecutor successfully");
        return executor;
    }

    @Bean
    public Executor asyncServiceExecutorWithMonitorExecutor(){
        ThreadPoolTaskExecutor executor = new MonitorThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadName);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        logger.info("initialized asyncServiceExecutorWithMonitorExecutor successfully");
        return executor;
    }

}
