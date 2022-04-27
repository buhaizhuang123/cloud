package com.cloud.shop.shop;

import com.cloud.shop.dto.Shop;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/4/27 11:45
 * @mark ShopDao
 */
public interface ShopDao {


    List<Shop> listShop(RowBounds rowBounds);

}
