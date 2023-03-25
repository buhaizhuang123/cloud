package com.cloud.event.dto;

import lombok.ToString;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * @author haizhuangbu
 * @date 2023/3/25 12:53
 * @mark MsgEvent
 */
@ToString
public class MsgEvent extends ApplicationContextEvent {

    private String message;

    private String code;


    public MsgEvent(ApplicationContext source) {
        super(source);
    }


    @Override
    public Object getSource() {
        return this;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
