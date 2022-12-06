package com.isaackubo.guava.demo5;

import ch.qos.logback.core.encoder.EchoEncoder;

public interface TargetMonitor {
    void startMonitor() throws Exception;

    void stopMonitor() throws Exception;
}
