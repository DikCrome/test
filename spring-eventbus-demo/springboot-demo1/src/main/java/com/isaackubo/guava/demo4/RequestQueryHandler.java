package com.isaackubo.guava.demo4;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestQueryHandler {
    private static final Logger logger = LoggerFactory.getLogger(QueryService.class);

    private EventBus eventBus;

    public RequestQueryHandler(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.register(this);
    }

    @Subscribe
    public void doQuery(Request request){
        logger.info("start query the orderNo [{}]", request.toString());
        final Response response = new Response();
        eventBus.post(response);
    }
}
