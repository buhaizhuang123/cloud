package com.cloud.shop.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.cloud.common.InfoUtils;
import com.cloud.shop.dto.Shop;
import com.cloud.shop.service.ShopService;
import com.cloud.shop.shop.ShopDao;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author haizhuangbu
 * @date 2022/4/27 11:24
 * @mark ShopController
 */
@RequestMapping("shop")
@RestController
//@NacosPropertySource(dataId = "1", autoRefreshed = true)
public class ShopController {

    @Value("${server.port}")
    private String serverProt;

//    @NacosValue("${nacosName}")
    private String nacosName;
//    @Value("${nacosName}")
    private String ncName;

    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopDao shopDao;

    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String show(HttpServletRequest request) {
        return serverProt + "show";
    }

    @RequestMapping("list")
    @HystrixCommand(fallbackMethod = "defaultFail")
    public List<Shop> listShop() {
        int i = 1 / 0;
        return shopService.getShops();
    }

    @RequestMapping("default")
    @SentinelResource("default")
    public List<Shop> defaultFail() {
        ArrayList<Shop> list = new ArrayList<>();
        Shop shop = new Shop();
        shop.setShopName("默认");
        shop.setShopAddr(nacosName);
        shop.setShopType(ncName);
        list.add(shop);
        return list;
    }

    @RequestMapping("send")
    public String sendInfo() {
        InfoUtils.send("usr", "我是消息");
        return "发送成功";
    }

    @RequestMapping("search")
    public Object search(@RequestParam("a") String a, @RequestParam("b") String b) {
        return shopDao.search(a, b);
    }


    public static void main(String[] args) {
        LinkedList<Object> objects = new LinkedList<>();

        objects.add("123");
        objects.add("1231");
        objects.add("12311");
        objects.add("123111");
        Object pop = objects.pollLast();
        for (Object object : objects) {
            System.out.println(object);
        }
    }

}
