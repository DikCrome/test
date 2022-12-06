package com.isaackubo.guava.demo2;

import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;

public class ExceptionHandler implements SubscriberExceptionHandler {
    @Override
    public void handleException(Throwable exception, SubscriberExceptionContext context) {
        System.out.println("event:" + context.getEvent());
        System.out.println("eventbus:" + context.getEventBus());
        System.out.println("subscriber:" + context.getSubscriber());
        System.out.println("subscriberMethod:" + context.getSubscriberMethod());
    }
}
