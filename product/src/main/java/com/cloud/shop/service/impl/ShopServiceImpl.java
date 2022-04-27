package com.cloud.shop.service.impl;

import com.cloud.shop.dto.Shop;
import com.cloud.shop.service.ShopService;
import com.cloud.shop.shop.ShopDao;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/4/27 11:44
 * @mark ShopServiceImpl
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    public List<Shop> getShops() {
        RowBounds rowBounds = new RowBounds(1, 10);
        return shopDao.listShop(rowBounds);
    }
}
