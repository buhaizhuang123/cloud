package com.cloud.timer.common;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.SockJsServiceRegistration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistration;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author haizhuangbu
 * @date 2022/5/31 14:30
 * @mark WebSocketConfig
 */
@Component
public class WebSocketServerConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 添加拦截地址以及相应的websocket消息处理器
//        WebSocketHandlerRegistration registration = registry.addHandler(new TimerOne(),"pages/mess/gc/Timer");
//        SockJsServiceRegistration sockJS = registration.withSockJS();
        // 添加拦截器
        registry.addHandler(new TimerOne(),"/connect").setAllowedOrigins("*");
    }
}
