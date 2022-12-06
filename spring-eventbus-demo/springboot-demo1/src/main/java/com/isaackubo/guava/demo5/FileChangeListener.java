package com.isaackubo.guava.demo5;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileChangeListener {

    private static final Logger logger = LoggerFactory.getLogger(FileChangeListener.class);

    @Subscribe
    public void onChange(FileChangeEvent fileChangeEvent){
        logger.info("{} - {}",fileChangeEvent.getPath(),
                fileChangeEvent.getKind().type() + ":" + fileChangeEvent.getKind().name());
    }
}
