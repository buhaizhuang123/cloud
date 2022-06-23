package com.cloud.kafka.lis;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.function.Function;

/**
 * @author haizhuangbu
 * @date 2022/5/29 13:12
 * @mark DmLis
 */
@Component
public class DmLis {

    @KafkaListener(topics = "usr", groupId = "demo")
    public void listen(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        System.out.println("value = " + value);
        ack.acknowledge();
    }
}
