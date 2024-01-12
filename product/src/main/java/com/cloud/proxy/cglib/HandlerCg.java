package com.cloud.proxy.cglib;

import com.cloud.proxy.mine.impl.HandlerRunImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author haizhuangbu
 * @date 2022/8/10 12:05
 * @mark HandlerCg
 */
public class HandlerCg implements MethodInterceptor {

    private Object object;

    public HandlerCg(Object object) {
        this.object = object;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理类");
        return methodProxy.invoke(object, objects);
    }


    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HandlerRunImpl.class);
        enhancer.setCallback(this);
        return enhancer.create();
    }
}
