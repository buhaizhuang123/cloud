package com.cloud.event.listen;

import com.cloud.event.dto.MsgEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author haizhuangbu
 * @date 2023/3/25 12:58
 * @mark MsgEventService
 */
@Service
@Slf4j
public class MsgEventService {


    @EventListener
    public void accept(MsgEvent msgEvent) {
        log.info("msgEvent : {}", msgEvent);
    }


}
