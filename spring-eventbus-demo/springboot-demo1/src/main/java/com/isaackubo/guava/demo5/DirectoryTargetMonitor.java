package com.isaackubo.guava.demo5;

import com.google.common.eventbus.EventBus;
import com.isaackubo.guava.demo4.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;

public class DirectoryTargetMonitor implements TargetMonitor {

    private static final Logger logger = LoggerFactory.getLogger(DirectoryTargetMonitor.class);

    public static final ConcurrentHashMap<String, Thread> threadMaps = new ConcurrentHashMap<>();

    private WatchService watchService;

    private EventBus eventBus;

    private Path path;

    private volatile boolean start;

    public DirectoryTargetMonitor(EventBus eventBus, String targetPath){
        this(eventBus, targetPath, "");
    }

    public DirectoryTargetMonitor(EventBus eventBus, String targetPath, String ... params){
        this.eventBus = eventBus;
        this.path = Paths.get(targetPath, params);
    }



    @Override
    public void startMonitor() throws Exception {
       this.watchService = FileSystems.getDefault().newWatchService();
       this.path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_DELETE,
               StandardWatchEventKinds.ENTRY_MODIFY);
       logger.info("the directory [{}] is monitoring ..." , path);
       start = true;
       System.out.println("startmonitor:" + Thread.currentThread().getName());
       threadMaps.put("工单id，这个工单id从前端传过来", Thread.currentThread());
       while(start){
           WatchKey watchKey = null;
           try{
               watchKey = watchService.take();
               watchKey.pollEvents().forEach(event -> {
                   final WatchEvent.Kind<?> kind = event.kind();
                   final Path path = (Path) event.context();
                   final Path child = this.path.resolve(path);
                   eventBus.post(new FileChangeEvent(kind, child));
               });
               if(Thread.currentThread().isInterrupted()){
                   this.start = false;
                   //或者break
               }
               //当在等待的时候会触发InterruptException
           }catch (Exception e){
                this.start = false;
           }finally {
               if(watchKey != null){
                   watchKey.reset();
               }
           }
       }
        System.out.println("watch end:" + start);
    }

    //两种实现方式：
    //1、更改start为false，这样调用就要在controller层或者说要保存的是Monitor
    //2、采用Thread的中断，再monitor对象中保存thread，调用thread.interrupt()，发送中断信号
    //如果程序正常运行，那么会进入判断当前线程是否有中断信号，将start置为false。如果在take的地方卡主了
    //那么会被抛出InterruptException的异常，在异常中将start置为false，或者直接break
    @Override
    public void stopMonitor() throws Exception {
        logger.info("The directory [{}] monitor will be stop ...", path );
        System.out.println("stopmonitor:" + Thread.currentThread().getName());
        final Thread thread = threadMaps.get("工单id，这个工单id从前端传过来");
        // 发送一个中断的信号
        thread.interrupt();
        this.start = false;
        this.watchService.close();
        logger.info("the directory [{}] monitor will be stop done", path);
    }
}
