package com.cloud.timer.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author haizhuangbu
 * @date 2022/5/31 14:06
 * @mark TimerOne
 */
public class TimerOne implements WebSocketHandler {


    private Logger logger = LoggerFactory.getLogger(TimerOne.class);

    /**
     * 保存用户连接
     */
    private static ConcurrentHashMap<String, WebSocketSession> map = new ConcurrentHashMap<String, WebSocketSession>();

    // 连接 就绪时
    // 连接成功时候，会触发UI上onopen方法
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        logger.info("链接就绪");
        String userName = (String) webSocketSession.getAttributes().get("HTTP.SESSION.userName");
        logger.info("userName:{}",userName);
        if (!map.isEmpty()) {
            for (Map.Entry<String, WebSocketSession> user : map.entrySet()) {
                if (user.getKey().equals(userName)) {
                    if (user.getValue().isOpen()) {
                        user.getValue().sendMessage(new TextMessage("您的账号在其他位置登录，当前连接已断开，如非本人操作请立即修改密码"));
                    }
                    user.getValue().close();
                    map.remove(user);
                    break;
                }
            }
        }

        map.put(userName, webSocketSession);
        String userNames = map.entrySet().stream().map(String::valueOf).collect(Collectors.joining());
        sendMessageToUsers(new TextMessage(userNames));
    }

    // 处理信息
    //在 UI在用js调用websocket.send()时候，会调用该方法，将message分发出去
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        sendMessageToUsers(webSocketMessage);
    }

    // 处理传输时异常
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        logger.error("错误信息:{}",throwable);
    }

    // 关闭 连接时
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        logger.info("关闭");
        map.remove(webSocketSession.getAttributes().get("HTTP.SESSION.userName"));
    }

    //是否支持分包
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (ConcurrentHashMap.Entry<String, WebSocketSession> user : map.entrySet()) {
            if (user.getKey().equals(userName)) {
                try {
                    if (user.getValue().isOpen()) {
                        user.getValue().sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(WebSocketMessage<?> message) {
        for (WebSocketSession user:map.values()) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                    System.out.println(user+"成功：给所有在线用户发送消息");
                }
            } catch (IOException e) {
                System.out.println("失败：给所有在线用户发送消息");
                e.printStackTrace();
            }
        }
    }
}
