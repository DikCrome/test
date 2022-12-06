package com.isaackubo.guava.demo1;

import com.google.common.eventbus.AsyncEventBus;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Provider {
//    @Autowired
//    private AsyncEventBus eventBus;

    public static void main(String[] args) {
//        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(2);
//        executor.setMaxPoolSize(10);
//        executor.setQueueCapacity(20);

        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(10,  10,
                        5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        AsyncEventBus bus = new AsyncEventBus("merchant-ayncbus", executor);
        final EventMessage message = EventMessage.builder().type(1).msg("message1").build();
        bus.register(new MessageHandler());
        bus.register(new MessageHandler2());
        bus.post(message);
        final EventMessage2 message2 = EventMessage2.builder().type(2).msg("message2").build();
        bus.post(message2);
    }
}
