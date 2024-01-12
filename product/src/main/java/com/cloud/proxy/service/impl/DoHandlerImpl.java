package com.cloud.proxy.service.impl;

import com.cloud.proxy.service.DoHandler;

/**
 * @author haizhuangbu
 * @date 2022/7/26 17:44
 * @mark DoHandlerImpl
 */
public class DoHandlerImpl implements DoHandler {
    @Override
    public String doHandler() {
        System.out.println("zhi xing ");
        return "zjix";
    }
}
