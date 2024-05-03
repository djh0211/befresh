package com.a307.befresh.global.config.batch;

import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.food.repository.FoodRepository;
import com.a307.befresh.module.domain.notification.service.NotificationService;
import com.a307.befresh.module.domain.refrigerator.Refrigerator;
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
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ExpireBatchConfig {
    private final JobLauncher jobLauncher;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final RefrigeratorRepository refrigeratorRepository;
    private final FoodRepository foodRepository;
    private final NotificationService notificationService;

    @Bean
    public Job sendFoodExpirationNotificationJob() {
        Step step = foodExpirationStep();
        return new JobBuilder("sendFoodExpirationNotificationJob", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public Step foodExpirationStep() {
        Tasklet tasklet = foodExpirationTasklet();
        return new StepBuilder("foodExpirationStep", jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }

    @Bean
    public Tasklet foodExpirationTasklet() {
        return (contribution, chunkContext) -> {
            sendNotificationsForDays(7);
            sendNotificationsForDays(1);
            sendNotificationsForDays(0);
            sendNotificationsForDays(-1);
            sendNotificationsForDays(-7);
            return RepeatStatus.FINISHED;
        };
    }

    private void sendNotificationsForDays(int daysBefore) {
        LocalDate targetDate = LocalDate.now().plusDays(daysBefore);
        List<Refrigerator> refrigeratorList = refrigeratorRepository.findAll();
        log.info("refrigeratorList = " + refrigeratorList);

        for (Refrigerator refrigerator : refrigeratorList) {
            List<Food> expireFoodList = foodRepository.findExpireFood(refrigerator.getId(), targetDate);
            log.info("expireFoodList = " + expireFoodList);
            notificationService.sendExpireNotification(refrigerator.getId(), expireFoodList, daysBefore);
        }
    }

    @Scheduled(cron = "0 0 6 * * ?") // 매일 오전 6시에 실행
    public void runJob() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        try {
            jobLauncher.run(sendFoodExpirationNotificationJob(), jobParameters);
            log.info("Job was successfully executed.");
        } catch (Exception e) {
            log.error("Error running job", e);
        }
    }
}







