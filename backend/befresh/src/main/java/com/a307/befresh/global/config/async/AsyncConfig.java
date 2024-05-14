package com.a307.befresh.global.config.async;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.VirtualThreadTaskExecutor;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "virtualExecutor")
    public Executor threadPoolTaskExecutor() {

        //        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
//
//        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
//        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
//        taskExecutor.setQueueCapacity(QUEUE_CAPACITY);
//        taskExecutor.setThreadNamePrefix("Executor-");

        return new VirtualThreadTaskExecutor();
    }
}