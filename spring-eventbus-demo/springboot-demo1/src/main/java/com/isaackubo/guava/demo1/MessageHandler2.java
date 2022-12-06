package com.isaackubo.guava.demo1;

import com.google.common.eventbus.Subscribe;

public class MessageHandler2 {

    @Subscribe
    public void consumer1(EventMessage message){
        System.out.println("listener 2");
    }
}
