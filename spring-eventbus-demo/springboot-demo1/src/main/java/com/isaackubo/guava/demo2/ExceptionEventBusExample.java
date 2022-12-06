package com.isaackubo.guava.demo2;

import com.google.common.eventbus.EventBus;

public class ExceptionEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus(new ExceptionHandler());
        eventBus.register(new ExceptionEventBusHandler());
        eventBus.post("hello");
    }
}
