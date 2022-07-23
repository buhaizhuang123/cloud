package com.cloud.chain.netty.dto;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author haizhuangbu
 * @date 2022/7/23 08:50
 * @mark GResponse
 */
public class GResponse {

    private ChannelHandlerContext ctx;
    private HttpRequest req;

    public GResponse(ChannelHandlerContext ctx, HttpRequest req) {
        this.req = req;
        this.ctx = ctx;
    }


    public void writer(String out) {
        try {
            if (StringUtils.isBlank(out)) return;

            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(out.getBytes(StandardCharsets.UTF_8)));

            response.headers().set("Content-Type", "text/html;");

            ctx.write(response);
        } finally {
            ctx.flush();
            ctx.close();
        }

    }
}
