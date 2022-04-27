package com.cloud.shop.controller;

import com.cloud.shop.dto.Shop;
import com.cloud.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "show",method = RequestMethod.GET)
    public String show(){
        return serverProt+ "show";
    }

    @RequestMapping("list")
    public List<Shop> listShop(){
        return shopService.getShops();
    }

}
