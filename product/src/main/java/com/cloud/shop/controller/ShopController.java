package com.cloud.shop.controller;

import com.cloud.common.InfoUtils;
import com.cloud.shop.dto.Shop;
import com.cloud.shop.service.ShopService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @RequestMapping(value = "show", method = RequestMethod.POST)
    public String show() {
        return serverProt + "show";
    }

    @RequestMapping("list")
    @HystrixCommand(fallbackMethod = "defaultFail")
    public List<Shop> listShop() {
        int i = 1 / 0;
        return shopService.getShops();
    }

    public List<Shop> defaultFail() {
        ArrayList<Shop> list = new ArrayList<>();
        Shop shop = new Shop();
        shop.setShopName("默认");
        list.add(shop);
        return list;
    }

    @RequestMapping("send")
    public String sendInfo() {
        InfoUtils.send("usr", "我是消息");
        return "发送成功";
    }


}
