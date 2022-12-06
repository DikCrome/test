package com.isaackubo.guava.demo6;

import org.omg.PortableInterceptor.SUCCESSFUL;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MyAsyncEventBus extends MyEventBus {

    private static final String DEFAULT_TOPIC = "DEFAULT_TOPIC";

    public MyAsyncEventBus(String busName, MyEventExceptionHandler myEventExceptionHandler, ThreadPoolExecutor executor) {
        super(busName,myEventExceptionHandler,executor);
    }

    public MyAsyncEventBus(String busName) {
        super(busName,null, Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2));
    }

    public MyAsyncEventBus(){
        this(DEFAULT_TOPIC);
    }

    public MyAsyncEventBus(ThreadPoolExecutor executor){
        this(DEFAULT_TOPIC, null, executor);
    }

    public MyAsyncEventBus(MyEventExceptionHandler eventExceptionHandler, ThreadPoolExecutor executor){
        this(DEFAULT_TOPIC, eventExceptionHandler, executor);
    }
}
