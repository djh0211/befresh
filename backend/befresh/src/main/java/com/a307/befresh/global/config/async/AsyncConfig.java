package com.a307.befresh.global.config.async;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.VirtualThreadTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    private int CORE_POOL_SIZE = 3;
    private int MAX_POOL_SIZE = 10;
    private int QUEUE_CAPACITY = 100_000;

    @Bean(name = "virtualExecutor")
    public Executor threadPoolTaskExecutor(){

        VirtualThreadTaskExecutor taskExecutor = new VirtualThreadTaskExecutor();
//        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
//
//        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
//        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
//        taskExecutor.setQueueCapacity(QUEUE_CAPACITY);
//        taskExecutor.setThreadNamePrefix("Executor-");

        return taskExecutor;
    }
}