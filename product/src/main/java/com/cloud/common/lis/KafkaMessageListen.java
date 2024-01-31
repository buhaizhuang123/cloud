package com.cloud.common.lis;

import com.cloud.common.vo.KafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author haizhuangbu
 * @date 2024/1/30 14:56
 * @mark KafkaMessageListern
 */
@Slf4j
@Component
public class KafkaMessageListen implements ApplicationListener<KafkaMessage> {
    @Override
    public void onApplicationEvent(KafkaMessage kafkaMessage) {
        log.info("事件触发");
        log.info("消息 : {}", kafkaMessage);
    }
}
