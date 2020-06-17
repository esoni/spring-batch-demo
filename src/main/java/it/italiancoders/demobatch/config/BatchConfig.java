package it.italiancoders.demobatch.config;

import it.italiancoders.demobatch.io.Reader;
import it.italiancoders.demobatch.io.Writer;
import it.italiancoders.demobatch.listener.JobCompletionListener;
import it.italiancoders.demobatch.processor.Processor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job processJob() {
        return jobBuilderFactory.get("processJob")
                .incrementer(new RunIdIncrementer()).listener(listener())
                .flow(orderStep1()).end().build();
    }

    @Bean
    public Step orderStep1() {
        return stepBuilderFactory.get("orderStep1").<String, String> chunk(10)
                .reader(new Reader()).processor(new Processor())
                .writer(new Writer())
                .taskExecutor(taskExecutor()).build();

    }

    @Bean
    public TaskExecutor taskExecutor(){
        return new SimpleAsyncTaskExecutor("spring_batch");
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionListener();
    }

}
