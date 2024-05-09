package com.a307.befresh.global.config.batch;

import com.a307.befresh.module.domain.food.Food;
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

import java.util.List;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class NotificationBatchConfig {
    private final JobLauncher jobLauncher;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final RefrigeratorRepository refrigeratorRepository;
    private final FoodRepository foodRepository;
    private final NotificationService notificationService;
    private final RefreshRepository refreshRepository;

    @Bean
    public Job sendNotificationJob() {
        return new JobBuilder("sendNotificationJob", jobRepository)
                .start(updateRefreshStep())
                .next(expireNotificationStep())
                .build();
    }

    @Bean
    public Step updateRefreshStep() {
        return new StepBuilder("updateRefreshStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("[updateRefreshStep] update data");

                    Food food = foodRepository.findById(38705L).get();
                    food.setRefresh(refreshRepository.findById(3L).get());
                    food.setPrevRefresh(refreshRepository.findById(1L).get());
                    foodRepository.save(food);

                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step expireNotificationStep() {
        return new StepBuilder("expireNotificationStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("[expireNotificationStep] notification");

                    List<Food> foodList = foodRepository.findExpireFood();

                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

//    @Scheduled(cron = "0 0 9 * * ?") // 매일 오전 9시에 알림 전송
    @Scheduled(fixedRate = 60000)
    public void runJob() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        try {
            jobLauncher.run(sendNotificationJob(), jobParameters);
            log.info("Job was successfully executed.");
        } catch (Exception e) {
            log.error("Error running job", e);
        }
    }
}







