package com.cloud.sne.controller;

import com.cloud.common.InfoUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haizhuangbu
 * @date 2022/8/3 09:25
 * @mark SendKfkController 发送数据
 */
@RequestMapping("kafka")
@RestController
public class SendKfkController {


    @RequestMapping("send")
    public String send(@RequestBody String info) {
        InfoUtils.send("kafka_temp", info);

        return "数据发送成功";
    }


}
