package com.bu.shop.service;

import com.bu.shop.dto.ShopDto;
import com.github.pagehelper.PageInfo;

/**
 * @author haizhuangbu
 * @date 2023/12/29 14:27
 * @mark ShopService
 */
public interface ShopService {

    PageInfo<ShopDto> listShop(Integer pageNum, Integer pageSize);

}
