package com.cloud.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * @author haizhuangbu
 * @date 2024/1/30 14:47
 * @mark springBeanUtils
 */
@Component
public class SpringBeanUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private SpringBeanUtils() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static <T> T getBean(String beanName, Class<T> clazz) {
        return SpringBeanUtils.applicationContext.getBean(beanName, clazz);
    }


    public static void pushEvent(ApplicationEvent applicationEvent) {
        SpringBeanUtils.applicationContext.publishEvent(applicationEvent);
    }


}
