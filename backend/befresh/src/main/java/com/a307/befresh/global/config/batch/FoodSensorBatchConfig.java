package com.a307.befresh.global.config.batch;

import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.food.repository.FoodRepository;
import com.a307.befresh.module.domain.food.service.FoodService;
import com.a307.befresh.module.domain.notification.service.NotificationService;
import com.a307.befresh.module.domain.refresh.repository.RefreshRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class FoodSensorBatchConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final FoodRepository foodRepository;
    private final NotificationService notificationService;
    private final RefreshRepository refreshRepository;
    private final FoodService foodService;

    @Bean
    public Job processSensorJob() {
        return new JobBuilder("processSensorJob", jobRepository)
                .start(findContainerStep())
                .build();
    }

    @Bean
    public Step findContainerStep() {   // pH값에따라 신선도 update
        return new StepBuilder("findContainerStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("updateRefreshStep");
                    // 센서 데이터 처리 로직
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step updateContainerStep() {   // 마지막 수정일에 따라 측정 안됨으로 update
        return new StepBuilder("updateContainerStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("updateContainerStep");
                    // 센서 데이터 처리 로직
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step sendNotificationStep() {
        return new StepBuilder("sendNotificationStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    ExecutionContext jobExecutionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
//
//                    List<Long> warnFoodIdList = (List<Long>) jobExecutionContext.get("warnFoodIdList");
//                    List<Long> dangerFoodIdList = (List<Long>) jobExecutionContext.get("dangerFoodIdList");

//                    List<Food> warnFoodList = foodRepository.findNotiFood(warnFoodIdList);
//                    List<Food> dangerFoodList = foodRepository.findNotiFood(dangerFoodIdList);

//                    notificationService.sendNotification(warnFoodList, "expire");
//                    notificationService.sendNotification(dangerFoodList, "expire");

                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
}







