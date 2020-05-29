package com.tiger.dubbo.service.impl;

import com.tiger.service.ZhxtService;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import java.util.Random;

@Component
@Service(group = "zhxt",version="0.0.1",timeout = 20000,retries = 3,loadbalance = "roundrobin",actives = 4)
public class ZhxtServiceImpl implements ZhxtService {
    private static final Logger LOG = LoggerFactory.getLogger(ZhxtServiceImpl.class);
    @Override
    public Object getCurstCredit(String name) {
        Random random = new Random();
        int money = random.nextInt(9) * 1000;
        LOG.info(name+"："+money);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return money;
    }
}
