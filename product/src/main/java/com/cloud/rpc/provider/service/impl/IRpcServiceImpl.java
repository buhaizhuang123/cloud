package com.cloud.rpc.provider.service.impl;


import com.cloud.rpc.provider.service.IRpcService;

/**
 * @author haizhuangbu
 * @date 2022/7/23 13:37
 * @mark IRpcServiceImpl
 */
public class IRpcServiceImpl implements IRpcService {
    @Override
    public Integer add(Integer a, Integer b) {
        return a + b;
    }
}
