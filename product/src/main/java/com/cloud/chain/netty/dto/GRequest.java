package com.cloud.chain.netty.dto;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author haizhuangbu
 * @date 2022/7/23 08:49
 * @mark GRequest
 */
@Data
public class GRequest {

    private ChannelHandlerContext ctx;

    private HttpRequest req;

    public GRequest(ChannelHandlerContext ctx, HttpRequest req) {
        this.req = req;
        this.ctx = ctx;
    }

    public String getUrl() {
        return req.uri();
    }


    public String getMethod() {
        return req.method().name();
    }


    public Map<String, List<String>> getParameters() {
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(req.uri());
        return queryStringDecoder.parameters();
    }

    public String getParameter(String key) {
        Map<String, List<String>> param = getParameters();

        List<String> list = param.get(key);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }


}
