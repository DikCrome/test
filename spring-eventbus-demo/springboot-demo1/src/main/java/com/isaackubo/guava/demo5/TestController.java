package com.isaackubo.guava.demo5;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;

@RestController
public class TestController {

    @Autowired
    private EventBus eventBus;

    public static final ConcurrentHashMap<String,DirectoryTargetMonitor> dicMaps = new ConcurrentHashMap();

    /**
     * 开启对文件的监听
     * @param id 文章工单ID号
     * @throws Exception
     */
    @GetMapping("startWatch")
    public void startWatch(@RequestParam("id") String id) throws Exception {
        final DirectoryTargetMonitor monitor = new DirectoryTargetMonitor(eventBus, "D:\\googleDownload");
        dicMaps.put(id, monitor);
        monitor.startMonitor();
    }

    @GetMapping("stopWatch")
    public void stopWatch(@RequestParam("id") String id) throws Exception {
        final DirectoryTargetMonitor directoryTargetMonitor = dicMaps.get(id);
        if(directoryTargetMonitor != null){
            directoryTargetMonitor.stopMonitor();
        }
    }
}
