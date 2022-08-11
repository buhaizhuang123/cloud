package com.cloud.proxy.mine.ts;

import com.cloud.proxy.mine.impl.HandlerRunImpl;
import com.cloud.proxy.mine.interfacte.HandlerRun;
import com.cloud.proxy.mine.proxy.HandlerProxy;

import java.lang.reflect.Proxy;

/**
 * @author haizhuangbu
 * @date 2022/8/10 11:48
 * @mark HrTs
 */
public class HrTs {

    public static void main(String[] args) {
        HandlerRunImpl handlerRun = new HandlerRunImpl();
        HandlerRun handlerRun1 = (HandlerRun)Proxy.newProxyInstance(handlerRun.getClass().getClassLoader(), handlerRun.getClass().getInterfaces(), new HandlerProxy(handlerRun));
        handlerRun1.run();
    }


}
