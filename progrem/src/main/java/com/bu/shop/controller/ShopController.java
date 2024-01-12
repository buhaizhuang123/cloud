package com.bu.shop.controller;

import com.bu.shop.dto.ShopDto;
import com.bu.shop.service.ShopService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haizhuangbu
 * @date 2023/12/29 14:27
 * @mark ShopController
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping("/list")
    public PageInfo<ShopDto> listShop(@ApiParam Integer pageNum, @ApiParam Integer pageSize) {
        return shopService.listShop(pageNum, pageSize);
    }

}
