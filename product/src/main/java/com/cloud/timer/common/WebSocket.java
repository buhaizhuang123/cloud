package com.cloud.timer.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: websocket 服务类
 */

/**
 * @ServerEndpoint 这个注解有什么作用？
 * <p>
 * 这个注解用于标识作用在类上，它的主要功能是把当前类标识成一个WebSocket的服务端
 * 注解的值用户客户端连接访问的URL地址
 */

@Slf4j
@Component
@ServerEndpoint("/websocket/{name}")
public class WebSocket {

    /**
     * 标识当前连接客户端的用户名
     */
    private String name;

    /**
     * 用于存所有的连接服务的客户端，这个对象存储是安全的
     */
    public static ConcurrentHashMap<String, Session> webSocketSet = new ConcurrentHashMap<>();


    @OnOpen
    public void OnOpen(Session session, @PathParam(value = "name") String name) {
        this.name = name;
        // name是用来表示唯一客户端，如果需要指定发送，需要指定发送通过name来区分
        webSocketSet.put(session.getId(), session);
        log.info("[WebSocket] 连接成功，当前连接人数为：={}", webSocketSet.size());
    }


    @OnClose
    public void OnClose(Session session) {
        webSocketSet.remove(session.getId());
        log.info("[WebSocket] 退出成功，当前连接人数为：={}", webSocketSet.size());
    }

    @OnMessage
    public void OnMessage(String message, Session session) throws IOException {
        URI requestURI = session.getRequestURI();
        String path = requestURI.getPath();
        System.out.println("path = " + path);
        for (Map.Entry<String, Session> socketEntry : webSocketSet.entrySet()) {
            Session toSession = socketEntry.getValue();
            if (!session.getId().equals(toSession.getId())) {
                toSession.getAsyncRemote().sendText(message);
            }
        }


    }

    /**
     * 群发
     *
     * @param message
     */
    public static void GroupSending(String message) {
        for (String name : webSocketSet.keySet()) {
            try {
                if (webSocketSet.get(name).isOpen()) {
                    webSocketSet.get(name).getBasicRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 指定发送
     *
     * @param name
     * @param message
     */
    public static void AppointSending(String name, String message) {
        try {
            if (webSocketSet.get(name).isOpen()) {
                webSocketSet.get(name).getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
