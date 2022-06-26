package com.cloud.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author haizhuangbu
 * @date 2022/6/26 10:43
 * @mark SpringContextUtils bean工具 通过getBean方式获取对象
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }


    public static <T> T getBean(String name, Class<T> aClass) {
        return applicationContext.getBean(name, aClass);
    }


}
