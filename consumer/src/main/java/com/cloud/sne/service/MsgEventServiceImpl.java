package com.cloud.sne.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

/**
 * @author haizhuangbu
 * @date 2023/3/25 21:17
 * @mark MsgEventServiceImpl
 */
@Service
public class MsgEventServiceImpl {


    @HystrixCommand(fallbackMethod = "back")
    public void doError() throws Exception {
        throw new Exception();
    }

    public void back() {
        System.out.println("back = " + "back");
    }


}
