package com.isaackubo.guava.demo1;

import com.google.common.eventbus.Subscribe;

public class MessageHandler {

    @Subscribe
    public void consumer1(EventMessage message){
        System.out.println("consumer1:" + message.getType() + ":" + message.getMsg());
    }

    @Subscribe
    public void consumer2(EventMessage2 message){
        System.out.println("consumer2:" + message.getType() + ":" + message.getMsg());
    }

    @Subscribe
    public void consumer3(EventMessage message){
        System.out.println("consumer3:" + message.getType() + ":" + message.getMsg());
    }
}
