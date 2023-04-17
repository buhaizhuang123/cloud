package com.design.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author haizhuangbu
 * @date 2023/4/17 12:06
 * @mark Proxy
 */
public class Proxy implements InvocationHandler {

    private Object object;

    public Proxy(Object obj) {
        this.object = obj;
    }

    public void before() {
        System.out.println("before");
    }

    public void after() {
        System.out.println("after");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(object, args);
        after();
        return result;

    }

    public static void main(String[] args) {


        Proxy01 o = (Proxy01) java.lang.reflect.Proxy.newProxyInstance(Proxy.class.getClassLoader(), new Class[]{Proxy01.class}, new Proxy(new Proxy02()));
        o.doSome();

    }

}
interface Proxy01 {

    void doSome();
}


class Proxy02 implements Proxy01{

    @Override
    public void doSome() {
        System.out.println("doSome");
    }
}

