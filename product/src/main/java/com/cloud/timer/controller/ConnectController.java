package com.cloud.timer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author haizhuangbu
 * @date 2022/5/31 14:36
 * @mark ConnectController
 */
@RequestMapping("connect")
@Controller
public class ConnectController {

    @RequestMapping("getPage")
    public String connect(){
        return "Timer";
    }

    @RequestMapping("send")
    @ResponseBody
    public String send(){
        return "发送完成";
    }


}
