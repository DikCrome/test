package com.isaackubo.guava.demo6;


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

/**
 * topic1 -> subscriber-subscribe
 *        -> subscriber-subscribe
 *        -> subscriber-subscribe
 *
 * topic2 -> subscriber-subscribe
 *        -> subscriber-subscribe
 *        -> subscriber-subscribe
 */
class MyRegistry {

    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<MySubscriber>> subscriberContainer
            = new ConcurrentHashMap<>();

    public void bind(Object subscriber){
        List<Method> subscribeMethods = getSubScribeMethods(subscriber);
        subscribeMethods.forEach(m -> tierSubscribe(subscriber, m));

    }

    private void tierSubscribe(Object subscriber, Method method){
        MySubscribe annotation = method.getAnnotation(MySubscribe.class);
        String topic = annotation.topic();
        subscriberContainer.computeIfAbsent(topic, key -> new ConcurrentLinkedQueue<>());
        subscriberContainer.get(topic).add(new MySubscriber(subscriber, method));
    }

    private List<Method> getSubScribeMethods(Object subscriber) {
        final ArrayList<Method> methodList =new ArrayList<>();
        Class<?> temp = subscriber.getClass();

        while(temp != null){
            Method[] subscriberMethods = temp.getDeclaredMethods();
            Arrays.stream(subscriberMethods)
                    .filter(method -> method.isAnnotationPresent(MySubscribe.class)
                            && method.getParameterCount() == 1
                            && method.getModifiers() == Modifier.PUBLIC)
                    .forEach(methodList::add);
            temp = temp.getSuperclass();
        }
        return methodList;
    }

    //判断是否存在topic
    public boolean exist(String topic){
        return subscriberContainer.get(topic) != null && subscriberContainer.get(topic).size() != 0;
    }

    public ConcurrentLinkedQueue<MySubscriber> scanSubscriber(String topic){
        return subscriberContainer.get(topic);
    }

    // 不是直接删除，类似逻辑删除
    public void unbind(Object subscriber){
        subscriberContainer.forEach((key, queue) -> {
            queue.forEach(s -> {
                if(s.getSubscribeObject() == subscriber){
                    s.unbind();
                }
            });
        });
    }
}
