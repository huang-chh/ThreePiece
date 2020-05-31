package com.tiger.dubbo.service;

import com.tiger.dubbo.config.TigerConfig;
import com.tiger.service.ZhxtService;
import com.tiger.utils.redis.Redises;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

@Component
public class InvokeZhxtService implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(InvokeZhxtService.class);
    @Reference(group = "zhxt")
    private ZhxtService zhxtService;
    @Override
    public void run(){
        LOG.info(Thread.currentThread().getName()+"read to execute");
        invokeZhxt();
    }

    private void invokeZhxt(){
        String task = Redises.listPop(false,"ms_zhxt_task");
        if(StringUtils.isBlank(task)){
//            LOG.info("there is no task");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            Object credit = zhxtService.getCurstCredit(task);
            LOG.info(task+"==========  got result is:"+credit);
            Redises.sadd("ms_zhxt_result",credit.toString());
        }
        //这里使用递归进行重复调用
        invokeZhxt();
    }

}
