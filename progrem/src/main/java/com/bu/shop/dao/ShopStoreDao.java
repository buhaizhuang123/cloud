package com.bu.shop.dao;

import com.bu.shop.dto.ShopStoreDto;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2024/1/6 12:38
 * @mark ShopStoreDao
 */
public interface ShopStoreDao {

    List<ShopStoreDto> listOrdersByUserId(String userId);

}
