package com.isaackubo.guava.demo6.test;

import com.isaackubo.guava.demo6.MyAsyncEventBus;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MyAsyncBusExample {
    public static void main(String[] args) {
        MyAsyncEventBus myEventBus = new MyAsyncEventBus((ThreadPoolExecutor) Executors.newFixedThreadPool(10));
        myEventBus.register(new MySimpleListener3());
        myEventBus.register(new MySimpleListener4());
        myEventBus.post(1234,"async-topic");

    }
}
