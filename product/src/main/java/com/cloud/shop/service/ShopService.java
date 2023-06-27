package com.cloud.shop.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.RowBounds;

/**
 * @author haizhuangbu
 * @date 2022/4/27 11:43
 * @mark ShopService
 */
public interface ShopService {

    JSONObject getShops(RowBounds rowBounds);

}
