package com.cloud.person.controller;

import com.cloud.common.vo.ResultVo;
import com.cloud.person.dto.ShopInfoDto;
import com.cloud.person.service.ShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haizhuangbu
 * @date 2024/1/17 16:05
 * @mark ShopController
 */
@RestController
@RequestMapping("/shopInfo")
public class ShopInfoController {

    @Autowired
    private ShopInfoService shopService;

    @RequestMapping(value = "/addShop", method = RequestMethod.POST)
    public ResultVo<String> addShopInfo(@RequestBody ShopInfoDto shopInfoDto) {
        return shopService.addShopInfo(shopInfoDto);
    }


}
