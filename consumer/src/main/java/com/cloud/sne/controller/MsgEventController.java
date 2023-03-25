package com.cloud.sne.controller;

import com.cloud.common.SpringContextUtils;
import com.cloud.event.dto.MsgEvent;
import com.cloud.event.listen.MsgEventService;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haizhuangbu
 * @date 2023/3/25 13:00
 * @mark MsgEventController
 */
@RequestMapping("/msg")
@RestController
public class MsgEventController {


    @RequestMapping("/push")
    public String push(String msg) {
        StaticApplicationContext applicationContext = new StaticApplicationContext();
        MsgEvent msgEvent = new MsgEvent(applicationContext);
        msgEvent.setCode("200");
        msgEvent.setMessage(msg);
        SpringContextUtils.publishEvent(msgEvent);
        return "成功";
    }

}
