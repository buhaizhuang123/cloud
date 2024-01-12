package com.design.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author haizhuangbu
 * @date 2023/4/17 11:06
 * @mark CglibProxy
 */
public class CglibProxy implements MethodInterceptor {

    private Object object;

    public CglibProxy(Object obj) {
        this.object = obj;
    }

    public void before() {
        System.out.println("before");
    }

    public void after() {
        System.out.println("after");
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = method.invoke(object, objects);
        after();
        return result;
    }
}


class Ts {


    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Proxy.class);
        enhancer.setCallback(new CglibProxy(new ProxyImpl()));
        Proxy proxy = (Proxy) enhancer.create();
        proxy.doSome();
    }
}

interface Proxy {

    public void doSome();

}

class ProxyImpl implements Proxy {


    @Override
    public void doSome() {
        {
            System.out.println("doSome");
        }
    }
}

