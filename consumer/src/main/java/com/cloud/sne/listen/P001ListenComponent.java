package com.cloud.sne.listen;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author haizhuangbu
 * @date 2023/3/25 18:03
 * @mark P001ListenComponent
 */
@Component
@Slf4j
public class P001ListenComponent {

    @KafkaListener(topics = "kafka_temp", groupId = "demo")
    public void accept(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        log.info("value:{}", value);
        ack.acknowledge();
    }


}
