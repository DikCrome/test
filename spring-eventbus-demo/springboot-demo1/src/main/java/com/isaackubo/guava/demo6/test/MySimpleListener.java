package com.isaackubo.guava.demo6.test;

import com.isaackubo.guava.demo6.MySubscribe;

public class MySimpleListener {

    @MySubscribe
    public void test1(String x){
        System.out.println("MySimpleListener == test1 ==" + x);
    }

    @MySubscribe(topic = "alex-topic")
    public void test2(Integer x){
        System.out.println("MySimpleListener == test2 ==" + x);
    }
}
