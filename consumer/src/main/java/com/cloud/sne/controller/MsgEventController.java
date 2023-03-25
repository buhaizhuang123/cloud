package com.cloud.sne.controller;

import com.cloud.common.InfoUtils;
import com.cloud.common.SpringContextUtils;
import com.cloud.event.dto.MsgEvent;
import com.cloud.event.listen.MsgEventService;
import com.cloud.sne.service.MsgEventServiceImpl;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author haizhuangbu
 * @date 2023/3/25 13:00
 * @mark MsgEventController
 */
@RequestMapping("/msg")
@RestController
public class MsgEventController {
    @Autowired
    private MsgEventServiceImpl service;


    @RequestMapping("/push")
    public String push(String msg, HttpServletRequest request) {
        StaticApplicationContext applicationContext = new StaticApplicationContext();
        MsgEvent msgEvent = new MsgEvent(applicationContext);
        msgEvent.setCode("200");
        msgEvent.setMessage(msg);
        SpringContextUtils.publishEvent(msgEvent);
        return "成功";
    }

    @RequestMapping("/push01")
    public String push(String msg) {
        InfoUtils.send("p001", msg);
        return "发送消息";
    }

    @RequestMapping("/error")
    public void doError() throws Exception {
        service.doError();
    }


}
