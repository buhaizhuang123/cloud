package com.cloud.chain.netty.service;

import com.cloud.chain.netty.dto.GRequest;
import com.cloud.chain.netty.dto.GResponse;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @author haizhuangbu
 * @date 2022/7/23 08:45
 * @mark BzTomcatHandler
 */
public class BzTomcatHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 读取数据
        if (msg instanceof HttpRequest) {

            System.out.println("in to .....");
            HttpRequest req = (HttpRequest) msg;

            GRequest request = new GRequest(ctx, req);
            GResponse response = new GResponse(ctx, req);
            // 实际业务逻辑处理
            String url = request.getUrl();
        }


    }
}
