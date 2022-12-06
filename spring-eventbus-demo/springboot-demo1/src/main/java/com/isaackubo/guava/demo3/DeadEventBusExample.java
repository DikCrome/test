package com.isaackubo.guava.demo3;

import com.google.common.eventbus.EventBus;

public class DeadEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus(){
            @Override
            public String toString() {
                return "DEAD EVENT BUS";
            }
        };
        eventBus.register(new DeadEventListener());
        eventBus.post("hello");
        eventBus.post(123);
    }
}
