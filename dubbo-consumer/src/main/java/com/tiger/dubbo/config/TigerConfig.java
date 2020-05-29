package com.tiger.dubbo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class TigerConfig implements ApplicationContextAware {
    private static ApplicationContext _applicationContext =null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("*******************获取自定义的IOC容器成功***********************");
        _applicationContext =applicationContext;
    }

    public static Object getBean(Class<?> c){
        return _applicationContext.getBean(c);
    }

}
