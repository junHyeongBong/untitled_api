package org.test.unknownproject.test.batch.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
public class SimpleJob1 {

    @Bean(name = "simpleJob3")
    public Job simpleJob2(JobRepository jobRepository, Step simpleStep1) {
         return new JobBuilder("simpleJob3", jobRepository)
                 .incrementer(new RunIdIncrementer()) //동일 파라미터인데 다시 실행하고 싶을때
                 .start(simpleStep1)
                 .build();
    }
    @Bean(name = "simpleStep2")
    @JobScope
    public Step simpleStep1(JobRepository jobRepository, Tasklet testTasklet, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("simpleStep2", jobRepository)
                .tasklet(testTasklet, platformTransactionManager).build();
    }

    @Bean
    @StepScope
    public Tasklet testTasklet(){
        return ((contribution, chunkContext) -> {
            log.info(">>>>> This is Step1");
            return RepeatStatus.FINISHED;
        });
    }
}
