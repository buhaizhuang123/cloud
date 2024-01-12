package com.bu.shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author haizhuangbu
 * @date 2023/12/29 14:33
 * @mark ShopDto
 */
@Data
@ApiModel
public class ShopDto {

    @ApiModelProperty(name = "商品名称")
    private String shopName;

    @ApiModelProperty(name = "背景颜色")
    private String backgroundColor;

    @ApiModelProperty(name = "图片地址")
    private String imgUrl;

    @ApiModelProperty(name = "初始数量")
    private BigDecimal num = BigDecimal.ZERO;

}
