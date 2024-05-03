package com.a307.befresh.global.config.batch;

import com.a307.befresh.module.domain.container.Container;
import com.a307.befresh.module.domain.container.repository.ContainerRepository;
import com.a307.befresh.module.domain.notification.service.NotificationService;
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

import java.util.List;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ContainerBatchConfig {
    private final JobLauncher jobLauncher;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final RefrigeratorRepository refrigeratorRepository;
    private final ContainerRepository containerRepository;
    private final NotificationService notificationService;

    @Bean
    public Job sendContainerRefreshNotificationJob() {
        Step step = containerRefreshStep();
        return new JobBuilder("sendContainerRefreshNotificationJob", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public Step containerRefreshStep() {
        Tasklet tasklet = containerRefreshTasklet();
        return new StepBuilder("containerRefreshStep", jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }

    @Bean
    public Tasklet containerRefreshTasklet() {
        return (contribution, chunkContext) -> {
            sendNotification();
            return RepeatStatus.FINISHED;
        };
    }

    private void sendNotification() {
        List<Container> containerList = containerRepository.findBadFood();
        notificationService.sendContainerRefreshNotification(containerList);
    }

    @Scheduled(cron = "0 0 */3 * * ?") // 매 3시간마다 실행
    public void runJob() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        try {
            jobLauncher.run(sendContainerRefreshNotificationJob(), jobParameters);
            log.info("Job was successfully executed.");
        } catch (Exception e) {
            log.error("Error running job", e);
        }
    }
}







