package com.cloud.proxy.mine.impl;

import com.cloud.proxy.mine.interfacte.HandlerRun;

/**
 * @author haizhuangbu
 * @date 2022/8/10 11:47
 * @mark HandlerRunImpl
 */
public class HandlerRunImpl implements HandlerRun {
    @Override
    public void run() {
        System.out.println("被代理类执行");
    }
}
