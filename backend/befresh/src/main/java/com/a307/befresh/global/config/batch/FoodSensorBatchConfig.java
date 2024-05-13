package com.a307.befresh.global.config.batch;

import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.food.repository.FoodRepository;
import com.a307.befresh.module.domain.food.service.FoodService;
import com.a307.befresh.module.domain.notification.service.NotificationService;
import com.a307.befresh.module.domain.refresh.Refresh;
import com.a307.befresh.module.domain.refresh.repository.RefreshRepository;
import com.a307.befresh.module.domain.refrigerator.repository.RefrigeratorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class FoodSensorBatchConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job processSensorJob() {
        return new JobBuilder("processSensorJob", jobRepository)
                .start(processSensorStep())
                .build();
    }

    @Bean
    public Step processSensorStep() {
        return new StepBuilder("processSensorStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("Processing sensor data...");
                    // 센서 데이터 처리 로직
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
}







