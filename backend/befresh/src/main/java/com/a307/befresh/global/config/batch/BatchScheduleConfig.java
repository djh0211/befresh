package com.a307.befresh.global.config.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@Configuration
//@EnableScheduling
@RequiredArgsConstructor
public class BatchScheduleConfig {
    private final JobLauncher jobLauncher;
    private final FoodSensorBatchConfig foodSensorBatchConfig;
    private final FoodExpireBatchConfig foodExpireBatchConfig;

//    @Scheduled(cron = "0 0 0/1 * * ?") // 매시간 실행
    public void runSensorJob() {
        runJob(foodSensorBatchConfig.processSensorJob(), "processSensorJob");
    }

//    @Scheduled(cron = "0 0 9 * * ?") // 매일 오전 9시에 실행
    public void runExpiredFoodJob() {
        runJob(foodExpireBatchConfig.processExpiredFoodJob(), "processExpiredFoodJob");
    }

    private void runJob(Job job, String jobName) {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
            log.info("Job '{}' was successfully executed.", jobName);
        } catch (Exception e) {
            log.error("Error running job '{}'", jobName, e);
        }
    }
}







