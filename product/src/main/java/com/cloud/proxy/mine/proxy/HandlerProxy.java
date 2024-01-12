package com.cloud.proxy.mine.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author haizhuangbu
 * @date 2022/8/10 11:48
 * @mark HandlerProxy 代理类
 */
public class HandlerProxy implements InvocationHandler {

    private Object target;

    public HandlerProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("被代理对象");
        return method.invoke(target, args);
    }
}
