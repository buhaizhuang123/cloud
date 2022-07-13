package com.cloud.shop.controller;

import com.cloud.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haizhuangbu
 * @date 2022/7/12 09:54
 * @mark SysController
 */
@RestController
@RequestMapping("sys")
public class SysController {
    @Autowired
    private ConsumerService consumerService;

    @RequestMapping("list")
    public Object list(){
        return consumerService.listShop();
    }


}
