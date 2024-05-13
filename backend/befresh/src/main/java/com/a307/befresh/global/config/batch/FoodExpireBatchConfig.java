package com.a307.befresh.global.config.batch;

import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.food.repository.FoodRepository;
import com.a307.befresh.module.domain.food.service.FoodService;
import com.a307.befresh.module.domain.notification.service.NotificationService;
import com.a307.befresh.module.domain.refresh.Refresh;
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
public class FoodExpireBatchConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final FoodRepository foodRepository;
    private final NotificationService notificationService;
    private final RefreshRepository refreshRepository;
    private final FoodService foodService;

    @Bean
    public Job processExpiredFoodJob() {
        return new JobBuilder("processExpiredFoodJob", jobRepository)
                .start(findExpireFoodStep())
                .next(updateFoodRefreshStep())
                .next(sendNotificationStep())
                .build();
    }

    @Bean
    public Step findExpireFoodStep() {
        return new StepBuilder("findExpireFoodStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("dangerFoodIdList");
                    ExecutionContext jobExecutionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();

                    List<Food> warnFoodList = foodRepository.findWarnFood();
                    List<Long> dangerFoodIdList = foodRepository.findDangerFood();

                    List<Long> warnFoodIdList = warnFoodList.stream()
                            .filter(food -> foodService.calculateRefresh(food) == 2)
                            .map(food -> food.getFoodId())
                            .toList();

                    jobExecutionContext.put("warnFoodIdList", warnFoodIdList);
                    jobExecutionContext.put("dangerFoodIdList", dangerFoodIdList);

                    System.out.println(warnFoodIdList);

                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step updateFoodRefreshStep() {
        return new StepBuilder("updateFoodRefreshStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    ExecutionContext jobExecutionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();

                    List<Long> warnFoodIdList = (List<Long>) jobExecutionContext.get("warnFoodIdList");
                    List<Long> dangerFoodIdList = (List<Long>) jobExecutionContext.get("dangerFoodIdList");

                    Refresh warnRefresh = refreshRepository.findById(2L).get();
                    Refresh dangerRefresh = refreshRepository.findById(3L).get();

                    List<Food> warnFoodList = foodRepository.findUpdateFood(warnFoodIdList);
                    for (Food food : warnFoodList) {
                        food.setPrevRefresh(food.getRefresh());
                        food.setRefresh(warnRefresh);
                    }

                    List<Food> dangerFoodList = foodRepository.findUpdateFood(dangerFoodIdList);
                    for (Food food : dangerFoodList) {
                        food.setPrevRefresh(food.getRefresh());
                        food.setRefresh(dangerRefresh);
                    }

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
                    List<Long> warnFoodIdList = (List<Long>) jobExecutionContext.get("warnFoodIdList");
                    List<Long> dangerFoodIdList = (List<Long>) jobExecutionContext.get("dangerFoodIdList");

                    List<Food> warnFoodList = foodRepository.findNotiFood(warnFoodIdList);
                    List<Food> dangerFoodList = foodRepository.findNotiFood(dangerFoodIdList);

                    notificationService.sendNotification(warnFoodList, "expire");
                    notificationService.sendNotification(dangerFoodList, "expire");

                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
}







