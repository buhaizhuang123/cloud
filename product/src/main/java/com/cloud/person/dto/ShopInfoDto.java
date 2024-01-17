package com.cloud.person.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author haizhuangbu
 * @date 2024/1/17 16:05
 * @mark ShopInfoDto
 */
@Data
public class ShopInfoDto {

    private String id;

    private String shopName;

    private BigDecimal price;

    private String sts;

    private Integer num;
}
