package com.cloud.person.controller;

import com.cloud.common.vo.ResultVo;
import com.cloud.person.dto.ShopInfoDto;
import com.cloud.person.service.ShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * @param shopInfoDto 商品信息
     * @return 添加商品是否成功
     */
    @RequestMapping(value = "/addShop", method = RequestMethod.POST)
    public ResultVo<String> addShopInfo(@RequestBody ShopInfoDto shopInfoDto) {
        return shopService.addShopInfo(shopInfoDto);
    }

    /**
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @return 分页展示商品信息
     */
    @RequestMapping("/listShop")
    public ResultVo<Page<ShopInfoDto>> listShop(Integer pageNum, Integer pageSize) {
        return shopService.listShop(pageNum, pageSize);
    }


}
