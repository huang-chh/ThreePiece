package com.tiger.dubbo.service;

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
    @Reference(group = "zhxt",version = "0.0.2")
    private ZhxtService zhxtService;
    @Override
    public void run(){
        String task = Redises.listPop(false,"ms_zhxt_task");
        if(StringUtils.isBlank(task)){
            LOG.info("there is no task");
        }else{
            LOG.info("read to execute task:"+task);
            Object credit = zhxtService.getCurstCredit(task);
            LOG.info(task+" got result is:"+credit);
            Redises.sadd("ms_zhxt_result",credit.toString());
        }
    }
}
