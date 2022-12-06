package com.isaackubo.guava.demo6.test;

import com.isaackubo.guava.demo6.MyEventBus;

public class MyEventBusExample {
    public static void main(String[] args) {
        MyEventBus myEventBus = new MyEventBus((cause, myEventContext) -> {
            cause.printStackTrace();
            System.out.println("=======================");
            System.out.println(myEventContext.getSource());
            System.out.println(myEventContext.getSubscriber());
            System.out.println(myEventContext.getSubscribe());
            System.out.println(myEventContext.getEvent());
        });
        myEventBus.register(new MySimpleListener());
        myEventBus.register(new MySimpleListener2());
        myEventBus.post(1234,"alex-topic");
    }
}
