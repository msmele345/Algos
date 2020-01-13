package com.algos.mitch.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.Executors
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor
import org.springframework.core.task.AsyncTaskExecutor
import org.springframework.scheduling.annotation.EnableAsync


@Configuration
@EnableAsync
class AsyncConfig {
    @Bean
    fun taskExecutor(): AsyncTaskExecutor {
        return ConcurrentTaskExecutor(Executors.newCachedThreadPool())
    }
}