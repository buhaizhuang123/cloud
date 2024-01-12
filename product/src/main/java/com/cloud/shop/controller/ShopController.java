package com.cloud.shop.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.cloud.common.InfoUtils;
import com.cloud.shop.dto.Shop;
import com.cloud.shop.service.ShopService;
import com.cloud.shop.shop.ShopDao;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author haizhuangbu
 * @date 2022/4/27 11:24
 * @mark ShopController
 */
@RequestMapping("shop")
@RestController
@NacosPropertySource(dataId = "1", autoRefreshed = true)
//@RefreshScope
public class ShopController {

    @Value("${server.port}")
    private String serverProt;

    @NacosValue("${nacosName}")
    private String nacosName;

    @NacosValue(value = "${nacosName}", autoRefreshed = true)
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
    public JSONObject listShop(@RequestBody RowBounds rowBounds) {
        return shopService.getShops(rowBounds);
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
        List<String> list = new ArrayList();
        return shopDao.search(a, b);
    }



}
