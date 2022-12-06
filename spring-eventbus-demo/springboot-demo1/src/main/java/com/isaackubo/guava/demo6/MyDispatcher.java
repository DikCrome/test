package com.isaackubo.guava.demo6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class MyDispatcher {

    private final Executor executorService;

    private final MyEventExceptionHandler exceptionHandler;

    public static final Executor SEQ_EXECUTOR_SERVICE = SeqExecutorService.INSTANCE;
    public static final Executor PER_THREAD_EXECUTOR_SERVICE = PerThreadExecutorService.INSTANCE;

    private MyDispatcher(Executor executorService, MyEventExceptionHandler exceptionHandler) {
        this.executorService = executorService;
        this.exceptionHandler = exceptionHandler;
    }

    static MyDispatcher newDispatcher(MyEventExceptionHandler exceptionHandler, Executor executor){
        return new MyDispatcher(executor, exceptionHandler);
    }

    static MyDispatcher seqDispatcher(MyEventExceptionHandler exceptionHandler){
        return new MyDispatcher(SEQ_EXECUTOR_SERVICE, exceptionHandler);
    }

    static MyDispatcher perDispatcher(MyEventExceptionHandler exceptionHandler){
        return new MyDispatcher(PER_THREAD_EXECUTOR_SERVICE, exceptionHandler);
    }

    public void dispatch(Bus bus, MyRegistry registry, Object event, String topic){
        //判断topic是否存在
        if(!registry.exist(topic)){
            if(exceptionHandler != null){
                exceptionHandler.handle(new IllegalArgumentException("The topic " + topic + " not bind yet"),
                        new BaseMyEventContext(bus.getBusName(),null, event));
            }
            return;
        }
        ConcurrentLinkedQueue<MySubscriber> subscribers = registry.scanSubscriber(topic);
        subscribers.stream().filter(sub -> !sub.isDisable())
                .filter(s -> {
                    Method method = s.getSubscribeMethod();
                    Class<?> parameterType = method.getParameterTypes()[0];
                    return parameterType.isAssignableFrom(event.getClass());
                })
                .forEach(s -> realInvokeSubscribe(s,event,bus));
    }

    private void realInvokeSubscribe(MySubscriber subscriber, Object event, Bus bus){
        Method subscribeMethod = subscriber.getSubscribeMethod();
        Object subscribeObject = subscriber.getSubscribeObject();
        executorService.execute(() -> {
            try {
                subscribeMethod.invoke(subscribeObject,event);
            } catch (Exception e) {
                if(exceptionHandler != null){
                    exceptionHandler.handle(e,new BaseMyEventContext(bus.getBusName(),subscriber,event));
                }
            }
        });
    }

    public void close(){
        if(executorService instanceof ExecutorService){
            ((ExecutorService) executorService).shutdown();
        }
    }

    // 串行处理
    private static class SeqExecutorService implements Executor{

        private static SeqExecutorService INSTANCE = new SeqExecutorService();

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }

    //FIXME
    private static class PerThreadExecutorService implements Executor{

        private static PerThreadExecutorService INSTANCE = new PerThreadExecutorService();

        @Override
        public void execute(Runnable command) {
            new Thread(command).start();
        }
    }

    private static class BaseMyEventContext implements MyEventContext {

        private final String eventBusName;

        private final MySubscriber subscriber;

        private final Object event;

        public BaseMyEventContext(String eventBusName, MySubscriber subscriber, Object event) {
            this.eventBusName = eventBusName;
            this.subscriber = subscriber;
            this.event = event;
        }

        @Override
        public String getSource() {
            return this.eventBusName;
        }

        @Override
        public Object getSubscriber() {
            return subscriber != null ? subscriber.getSubscribeObject() : null;
        }

        @Override
        public Method getSubscribe() {
            return subscriber != null ? subscriber.getSubscribeMethod() : null;
        }

        @Override
        public Object getEvent() {
            return this.event;
        }
    }
}
