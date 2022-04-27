package com.cloud.shop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haizhuangbu
 * @date 2022/4/27 11:24
 * @mark ShopController
 */
@RequestMapping("shop")
@RestController
public class ShopController {

    @Value("${server.port}")
    private String serverProt;

    @RequestMapping(value = "show",method = RequestMethod.GET)
    public String show(){
        return serverProt+ "show";
    }

}
