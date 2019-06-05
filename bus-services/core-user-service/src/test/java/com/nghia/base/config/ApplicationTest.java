package com.nghia.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * <b>Beans will be defined here</b>
 */
@Configuration
@ComponentScan(basePackages = {
        "com.nghia.tut.controller",
        "com.nghia.tut.domain",
        "com.nghia.tut.infrastructure"
})
@ComponentScan
public abstract class ApplicationTest {

    @Primary
    @Bean
    public TaskExecutor getTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return taskExecutor;
    }

}
