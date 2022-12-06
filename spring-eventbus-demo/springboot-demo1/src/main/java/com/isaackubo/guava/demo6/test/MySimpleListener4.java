package com.isaackubo.guava.demo6.test;

import com.isaackubo.guava.demo6.MySubscribe;

public class MySimpleListener4 {


    @MySubscribe(topic = "async-topic")
    public void asyncTest(Integer x) throws InterruptedException {
        System.out.println("MySimpleListener4 == asyncTest ==" + x);
    }

    @MySubscribe(topic = "async-topic")
    public void asyncTest2(Integer x) throws InterruptedException {
        System.out.println("MySimpleListener4 == asyncTest2 ==" + x);
    }

}
