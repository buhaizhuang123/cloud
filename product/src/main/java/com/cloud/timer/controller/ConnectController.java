package com.cloud.timer.controller;

import com.cloud.timer.common.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.Session;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author haizhuangbu
 * @date 2022/5/31 14:36
 * @mark ConnectController
 */
@RequestMapping("connect")
@Controller
public class ConnectController {

    @RequestMapping("getPage")
    public String connect() {
        return "Timer";
    }

    @RequestMapping("sendAll")
    @ResponseBody
    public String send(@RequestParam("message") String message) {
        ConcurrentHashMap<String, Session> webSocketSet = WebSocket.webSocketSet;
        if (!CollectionUtils.isEmpty(webSocketSet)) {
            for (Map.Entry<String, Session> session : webSocketSet.entrySet()) {
                Session value = session.getValue();
                value.getAsyncRemote().sendText(message);
            }
        }
        return "发送完成";
    }


}
