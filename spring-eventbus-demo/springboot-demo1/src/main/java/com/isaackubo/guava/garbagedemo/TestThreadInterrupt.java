package com.isaackubo.guava.garbagedemo;

public class TestThreadInterrupt {

    public static void main(String[] args) {
        Thread.interrupted();
        final boolean interrupted = Thread.currentThread().isInterrupted();
        System.out.println(interrupted);
    }
}
