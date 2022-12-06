package com.isaackubo.guava.demo3;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

public class DeadEventListener {

    @Subscribe
    public void dead(DeadEvent deadEvent){
        System.out.println(deadEvent.getEvent());
        System.out.println(deadEvent.getSource());
    }
}
