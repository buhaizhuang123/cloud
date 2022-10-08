package com.cloud.batch;

import com.cloud.batch.processor.MyitemProcessor;
import com.cloud.batch.reader.MyItemReader;
import com.cloud.batch.writer.MyItemWriter;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author haizhuangbu
 * @date 2022/10/7 09:39
 * @mark BatchConfig
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public MyItemReader reader() {
        return new MyItemReader();
    }


    @Bean
    public MyItemWriter writer() {
        return new MyItemWriter();
    }


    @Bean
    public MyitemProcessor processor() {
        return new MyitemProcessor();
    }

    @Bean
    public Step myStep() {
        return stepBuilderFactory.get("step1")
                .<String, String>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer()).build();
    }

    @Bean
    public Job myJob() {
        return jobBuilderFactory.get("MyJob")
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {

                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                            System.out.println("OK");
                        }
                    }
                }).flow(myStep()).end().build();
    }


}
