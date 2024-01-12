package com.cloud.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cloud.shop.dto.Shop;
import com.cloud.shop.service.ShopService;
import com.cloud.shop.shop.ShopDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public JSONObject getShops(RowBounds rowBounds) {
        PageHelper.startPage(rowBounds.getOffset(),rowBounds.getLimit());
        List<Shop> shops = shopDao.listShop();
        PageInfo<Shop> shopPageInfo = new PageInfo<>(shops);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list",shopPageInfo.getList());
        jsonObject.put("total",shopPageInfo.getTotal());
        return jsonObject;
    }
}
