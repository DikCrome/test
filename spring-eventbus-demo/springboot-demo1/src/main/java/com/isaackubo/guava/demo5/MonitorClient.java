package com.isaackubo.guava.demo5;

import com.google.common.eventbus.EventBus;

public class MonitorClient {
    public static void main(String[] args) throws Exception {
        final EventBus eventBus = new EventBus();
        eventBus.register(new FileChangeListener());
        final DirectoryTargetMonitor monitor = new DirectoryTargetMonitor(eventBus, "D:\\googleDownload");
        new Thread(() -> {
            try {
                Thread.sleep(10000);
                monitor.stopMonitor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        monitor.startMonitor();

    }
}
