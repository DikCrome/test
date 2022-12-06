package com.isaackubo.guava.demo6.test;

import com.isaackubo.guava.demo6.MySubscribe;

public class MySimpleListener3 {


    @MySubscribe(topic = "async-topic")
    public void asyncTest(Integer x) throws InterruptedException {
        Thread.sleep(5);
        System.out.println("MySimpleListener3 == asyncTest ==" + x);
    }

    @MySubscribe(topic = "async-topic")
    public void asyncTest2(String x) throws InterruptedException {
        System.out.println("MySimpleListener3 == asyncTest ==" + x);
    }

}
