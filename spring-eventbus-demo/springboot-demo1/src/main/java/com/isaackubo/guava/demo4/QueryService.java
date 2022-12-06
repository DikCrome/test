package com.isaackubo.guava.demo4;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryService {
    private static final Logger logger = LoggerFactory.getLogger(QueryService.class);

    private EventBus eventBus;

    public QueryService(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.register(this);
    }

    public void query(String orderNo){
        logger.info("Received the orderNo [{}]", orderNo);
        this.eventBus.post(new Request(orderNo));
    }

    @Subscribe
    public void handlerResponse(Response response){
        logger.info("response");
    }

}
