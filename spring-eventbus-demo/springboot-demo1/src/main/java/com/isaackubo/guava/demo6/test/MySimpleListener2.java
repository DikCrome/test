package com.isaackubo.guava.demo6.test;

import com.isaackubo.guava.demo6.MySubscribe;

public class MySimpleListener2 {

    @MySubscribe
    public void test1(String x){
        System.out.println("MySimpleListener2 == test1 ==" + x);
    }

    @MySubscribe(topic = "alex-topic")
    public void test2(Integer x){
        System.out.println("MySimpleListener2 == test2 ==" + x);
    }

    @MySubscribe(topic = "test-topic")
    public void test3(Integer x){
        throw new RuntimeException("throw exception test3");
    }
}
