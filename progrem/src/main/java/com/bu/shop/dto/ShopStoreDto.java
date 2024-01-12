package com.bu.shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author haizhuangbu
 * @date 2024/1/6 12:38
 * @mark ShopStoreDto
 */
@ApiModel
public class ShopStoreDto {

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("商品名称")
    private String orderMark;

    @ApiModelProperty("商品id")
    private String shopId;

    @ApiModelProperty("商品状态")
    private String shopSts;

    @ApiModelProperty("商品备注")
    private String shopMark;

    @ApiModelProperty("商品数量")
    private Integer shopNum;


    private Date createTime;

    private Date modifyTime;


}
