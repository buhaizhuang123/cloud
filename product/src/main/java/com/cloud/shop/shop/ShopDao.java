package com.cloud.shop.shop;

import com.cloud.shop.dto.Shop;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/4/27 11:45
 * @mark ShopDao
 */
public interface ShopDao {


    List<Shop> listShop();

    List<Shop> search(@Param("s1") String... s1);


}
