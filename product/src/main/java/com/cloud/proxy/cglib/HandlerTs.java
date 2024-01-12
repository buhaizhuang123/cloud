package com.cloud.proxy.cglib;

import com.cloud.proxy.mine.impl.HandlerRunImpl;
import com.cloud.proxy.mine.interfacte.HandlerRun;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author haizhuangbu
 * @date 2022/8/10 12:11
 * @mark HandlerTs
 */
public class HandlerTs {

    public static void main(String[] args) {
        HandlerRunImpl handlerRun = new HandlerRunImpl();
        HandlerCg handlerCg = new HandlerCg(handlerRun);
        HandlerRun handlerRun1 = (HandlerRun)handlerCg.getProxy();
        handlerRun1.run();
    }

}
