package com.tiger.dubbo.core;

import com.tiger.dubbo.config.TigerConfig;
import com.tiger.dubbo.service.InvokeZhxtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class InvokeThreadPool {
    private static final Logger LOG = LoggerFactory.getLogger(InvokeThreadPool.class);
    private static ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(1);
    private static int process = Runtime.getRuntime().availableProcessors();
    private static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(process,process * 2,30L,TimeUnit.SECONDS,
                                                                        new ArrayBlockingQueue(100),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
    private static final int invokePeer = 8;
    public static  void invoke(){
        InvokeZhxtService service = (InvokeZhxtService)TigerConfig.getBean(InvokeZhxtService.class);
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                LOG.info("**********定时任务开启**********");
                for (int i=0; i<invokePeer ; i++){
//                    InvokeZhxtService service = new InvokeZhxtService();
                    poolExecutor.submit(service);
                }
                LOG.info("**********定时任务结束**********");
            }
        },30,30, TimeUnit.SECONDS);
    }

}
