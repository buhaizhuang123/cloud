package com.cloud.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * @author haizhuangbu
 * @date 2022/6/18 18:11
 * @mark MessageEvent
 */
public class MessageEvent extends ApplicationContextEvent {

    private String message;

    public MessageEvent(ApplicationContext source) {
        super(source);
    }

    public MessageEvent(String message, ApplicationContext source) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
