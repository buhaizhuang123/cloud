package com.bu.shop.service.impl;

import com.bu.shop.dao.ShopDao;
import com.bu.shop.dto.ShopDto;
import com.bu.shop.service.ShopService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/12/29 14:28
 * @mark ShopServiceImpl
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    /**
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @return 商品信息
     */
    @Override
    public PageInfo<ShopDto> listShop(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<ShopDto> shopDtos = shopDao.listShop();
        PageInfo<ShopDto> shopDtoPageInfo = new PageInfo<>(shopDtos);

        return shopDtoPageInfo;
    }
}
