package com.cloud.rpc.consumer.proxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.Data;

/**
 * @author haizhuangbu
 * @date 2022/7/23 13:31
 * @mark RpcProxyHandler
 */
@Data
public class RpcProxyHandler extends ChannelInboundHandlerAdapter {

    private Object response;


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        this.response = msg;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getCause());
    }
}
