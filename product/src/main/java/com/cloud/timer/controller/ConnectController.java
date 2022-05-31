package com.cloud.timer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author haizhuangbu
 * @date 2022/5/31 14:36
 * @mark ConnectController
 */
@RequestMapping("connect")
@Controller
public class ConnectController {

    @RequestMapping("con1")
    public String connect(){
        return "Timer";
    }


}
