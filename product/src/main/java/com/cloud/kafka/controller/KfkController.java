package com.cloud.kafka.controller;

import com.cloud.common.vo.KafkaMessage;
import com.cloud.config.SpringBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haizhuangbu
 * @date 2022/5/29 13:08
 * @mark KfkController
 */
@RequestMapping("/kfk")
@RestController
public class KfkController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @RequestMapping("send")
    public String send(@RequestParam("info") String info) {
        kafkaTemplate.send("usr", info);
        return "消息发送成功";
    }

    @RequestMapping("/sendEvent")
    public void sendEvent() {
        KafkaMessage xxxxx = new KafkaMessage("xxxxx");
        SpringBeanUtils.pushEvent(xxxxx);
    }

}
