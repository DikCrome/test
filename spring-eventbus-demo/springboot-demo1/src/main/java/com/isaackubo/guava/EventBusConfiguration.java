package com.isaackubo.guava;

import com.google.common.eventbus.AsyncEventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

//@Configuration
//public class EventBusConfiguration {
//
//    @Bean
//    public AsyncEventBus asyncEventBus(){
//        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(2);
//        executor.setMaxPoolSize(10);
//        executor.setQueueCapacity(20);
//
//        return new AsyncEventBus("merchant-ayncbus", executor);
//    }
//}
