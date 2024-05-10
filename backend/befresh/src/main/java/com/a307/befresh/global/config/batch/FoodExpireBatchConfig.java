package com.a307.befresh.global.config.batch;

import com.a307.befresh.module.domain.food.repository.FoodRepository;
import com.a307.befresh.module.domain.notification.service.NotificationService;
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
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class FoodExpireBatchConfig {
    private final JobLauncher jobLauncher;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final RefrigeratorRepository refrigeratorRepository;
    private final FoodRepository foodRepository;
    private final NotificationService notificationService;
    private final RefreshRepository refreshRepository;

    @Bean
    public Job processExpiredFoodJob() {
        return new JobBuilder("processExpiredFoodJob", jobRepository)
                .start(findExpireFoodStep())
//                .next(updateFoodRefreshStep())
//                .next(sendNotificationStep())
                .build();
    }

    @Bean
    public Step findExpireFoodStep() {
        return new StepBuilder("findExpireFoodStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
//                    List<Food> warnFoodList = foodRepository.findDangerChangedFood(0.5);  // TODO : 신선 -> 주의 QueryDsl 작성 필요
//                    List<Long> dangerFoodIdList = foodRepository.findDangerChangedFood();
//
//                    ExecutionContext jobExecutionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
//                    jobExecutionContext.put("dangerFoodList", dangerFoodList);

                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step updateFoodRefreshStep() {
        return new StepBuilder("updateFoodRefreshStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {

//                    ExecutionContext stepContext = contribution.getStepExecution().getExecutionContext();
//                    List<Food> dangerFoodList = (List<Food>) stepContext.get("dangerFoodList");
//                    Refresh dangerRefresh = refreshRepository.findById(3L).get();
//
//                    if (dangerFoodList != null) {
//                        for (Food food : dangerFoodList) {
//                            food.setPrevRefresh(food.getRefresh());
//                            food.setRefresh(dangerRefresh);
//                            foodRepository.save(food);
//                        }
//                    }

                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step sendNotificationStep() {
        return new StepBuilder("sendNotificationStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
//                    ExecutionContext stepContext = contribution.getStepExecution().getExecutionContext();
//
//                    List<Food> dangerFoodList = (List<Food>) stepContext.get("dangerFoodList");
//                    notificationService.sendExpireNotification(dangerFoodList, "danger");

                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Scheduled(cron = "0 0 9 * * ?") // 매일 오전 9시에 알림 전송
//    @Scheduled(fixedRate = 60000)
    public void runJob() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        try {
            jobLauncher.run(processExpiredFoodJob(), jobParameters);
            log.info("Job was successfully executed.");
        } catch (Exception e) {
            log.error("Error running job", e);
        }
    }
}







