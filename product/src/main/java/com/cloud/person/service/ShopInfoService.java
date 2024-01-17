package com.cloud.person.service;

import com.cloud.common.vo.ResultVo;
import com.cloud.person.dto.ShopInfoDto;

/**
 * @author haizhuangbu
 * @date 2024/1/17 16:07
 * @mark ShopService
 */
public interface ShopInfoService {

    ResultVo<String> addShopInfo(ShopInfoDto shopInfoDto);

}
