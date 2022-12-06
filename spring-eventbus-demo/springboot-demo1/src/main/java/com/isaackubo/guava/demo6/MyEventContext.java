package com.isaackubo.guava.demo6;

import java.lang.reflect.Method;

public interface MyEventContext {

    String getSource();

    Object getSubscriber();

    Method getSubscribe();

    Object getEvent();
}
