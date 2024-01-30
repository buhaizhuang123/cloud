package com.cloud.common.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @author haizhuangbu
 * @date 2024/1/30 14:53
 * @mark KafkaMessage
 */
@Getter
@Setter
public class KafkaMessage extends ApplicationEvent {

    private String message;

    public KafkaMessage(String message) {
        super(message);
        this.message = message;
    }
}
