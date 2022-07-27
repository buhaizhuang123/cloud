package com.cloud.proxy.service;

import com.cloud.person.dto.PersonDto;
import com.cloud.proxy.service.impl.DoHandlerImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

/**
 * @author haizhuangbu
 * @date 2022/7/26 17:35
 * @mark HandlerProxy
 */
public class HandlerProxy implements InvocationHandler {


    private Object target;


    public HandlerProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("实现代理");
        return method.invoke(target,args);
    }

    public static void main(String[] args) {

        DoHandler doHandler1 = new DoHandlerImpl();
        DoHandler doHandler = (DoHandler) Proxy.newProxyInstance(doHandler1.getClass().getClassLoader(),doHandler1.getClass().getInterfaces(),new HandlerProxy(doHandler1));
        doHandler.doHandler();


    }

}
