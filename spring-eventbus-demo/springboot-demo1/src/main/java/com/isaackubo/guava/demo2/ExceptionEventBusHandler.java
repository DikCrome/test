package com.isaackubo.guava.demo2;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionEventBusHandler {

    public static final Logger logger = LoggerFactory.getLogger(ExceptionEventBusHandler.class);

    @Subscribe
    public void m1(String msg){
        System.out.println(msg);
    }

    @Subscribe
    public void exception(String message){
        throw new RuntimeException();
    }
}
