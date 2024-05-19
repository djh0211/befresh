package com.a307.befresh.global.config.batch;

import com.a307.befresh.module.domain.container.repository.ContainerRepository;
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
public class FoodSensorBatchConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ContainerRepository containerRepository;
    private final FoodRepository foodRepository;
    private final NotificationService notificationService;
    private final RefreshRepository refreshRepository;

    @Bean
    public Job processSensorJob() {
        return new JobBuilder("processSensorJob", jobRepository)
                .start(findContainerStep())
                .next(updateContainerStep())
                .next(sendContainerNotificationStep())
                .build();
    }

    @Bean
    public Step findContainerStep() {
        return new StepBuilder("findContainerStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("updateRefreshStep");
                    ExecutionContext jobExecutionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();

                    List<Long> freshIdList = containerRepository.findFreshContainer();
                    List<Long> warnIdList = containerRepository.findWarnContainer();
                    List<Long> dangerIdList = containerRepository.findDangerContainer();
                    List<Long> noUpdateIdList = containerRepository.findNoUpdateContainer();
                    List<Long> reUpdateIdList = containerRepository.findReUpdateContainer();

                    // 센서 데이터 처리 로직
                    jobExecutionContext.put("freshIdList", freshIdList);
                    jobExecutionContext.put("warnIdList", warnIdList);
                    jobExecutionContext.put("dangerIdList", dangerIdList);
                    jobExecutionContext.put("noUpdateIdList", noUpdateIdList);
                    jobExecutionContext.put("reUpdateIdList", reUpdateIdList);

                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step updateContainerStep() {   // 마지막 수정일에 따라 측정 안됨으로 update
        return new StepBuilder("updateContainerStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    ExecutionContext jobExecutionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();

                    Refresh freshRefresh = refreshRepository.findById(1L).get();
                    Refresh warnRefresh = refreshRepository.findById(2L).get();
                    Refresh dangerRefresh = refreshRepository.findById(3L).get();
                    Refresh reUpdateRefresh = refreshRepository.findById(4L).get();
                    Refresh noUpdateRefresh = refreshRepository.findById(5L).get();

                    List<Long> freshIdList = (List<Long>) jobExecutionContext.get("freshIdList");
                    List<Long> warnIdList = (List<Long>) jobExecutionContext.get("warnIdList");
                    List<Long> dangerIdList = (List<Long>) jobExecutionContext.get("dangerIdList");
                    List<Long> noUpdateIdList = (List<Long>) jobExecutionContext.get("noUpdateIdList");
                    List<Long> reUpdateIdList = (List<Long>) jobExecutionContext.get("reUpdateIdList");

                    List<Food> freshFoodList = foodRepository.findUpdateFood(freshIdList);
                    for (Food food : freshFoodList) {
                        food.setPrevRefresh(food.getRefresh());
                        food.setRefresh(freshRefresh);
                        foodRepository.save(food);
                    }

                    List<Food> warnFoodList = foodRepository.findUpdateFood(warnIdList);
                    for (Food food : warnFoodList) {
                        food.setPrevRefresh(food.getRefresh());
                        food.setRefresh(warnRefresh);
                        foodRepository.save(food);
                    }

                    List<Food> dangerFoodList = foodRepository.findUpdateFood(dangerIdList);
                    for (Food food : dangerFoodList) {
                        food.setPrevRefresh(food.getRefresh());
                        food.setRefresh(dangerRefresh);
                        foodRepository.save(food);
                    }

                    List<Food> noUpdateFoodList = foodRepository.findUpdateFood(noUpdateIdList);
                    for (Food food : noUpdateFoodList) {
                        food.setPrevRefresh(food.getRefresh());
                        food.setRefresh(noUpdateRefresh);
                        foodRepository.save(food);
                    }

                    List<Food> reUpdateFoodList = foodRepository.findUpdateFood(reUpdateIdList);
                    for (Food food : reUpdateFoodList) {
                        food.setPrevRefresh(food.getRefresh());
                        food.setRefresh(reUpdateRefresh);
                        foodRepository.save(food);
                    }

                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step sendContainerNotificationStep() {
        return new StepBuilder("sendContainerNotificationStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    ExecutionContext jobExecutionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();

                    List<Long> freshIdList = (List<Long>) jobExecutionContext.get("freshIdList");
                    List<Long> warnIdList = (List<Long>) jobExecutionContext.get("warnIdList");
                    List<Long> dangerIdList = (List<Long>) jobExecutionContext.get("dangerIdList");
                    List<Long> noUpdateIdList = (List<Long>) jobExecutionContext.get("noUpdateIdList");

                    List<Food> freshFoodList = foodRepository.findNotiFood(freshIdList);
                    List<Food> warnFoodList = foodRepository.findNotiFood(warnIdList);
                    List<Food> dangerFoodList = foodRepository.findNotiFood(dangerIdList);
                    List<Food> noUpdateFoodList = foodRepository.findNotiFood(noUpdateIdList);

                    notificationService.sendContainerNotification(freshFoodList, "fresh");
                    notificationService.sendContainerNotification(warnFoodList, "warn");
                    notificationService.sendContainerNotification(dangerFoodList, "danger");
                    notificationService.sendContainerNotification(noUpdateFoodList, "noUpdate");

                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
}







