package com.isaackubo.guava.demo4;

import com.google.common.eventbus.EventBus;

public class CommunicationExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        final QueryService queryService = new QueryService(eventBus);
        final RequestQueryHandler requestQueryHandler = new RequestQueryHandler(eventBus);
        queryService.query("1234");
    }
}
