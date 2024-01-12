package com.cloud.kafka.service;

import com.cloud.kafka.event.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author haizhuangbu
 * @date 2022/6/18 18:14
 * @mark MessageEventService
 */
@Service
@Slf4j
public class MessageEventService {

    @EventListener
    public void accept(MessageEvent messageEvent) {
        log.info("接收消息:{}", messageEvent.getMessage());
    }

}
