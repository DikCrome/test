package com.isaackubo.guava.demo5.config;

import com.google.common.eventbus.EventBus;
import com.isaackubo.guava.demo5.FileChangeListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventBusConfiguration {

    @Bean
    public EventBus eventBus(){
        final EventBus eventBus = new EventBus();
        eventBus.register(new FileChangeListener());
        return eventBus;
    }

}
