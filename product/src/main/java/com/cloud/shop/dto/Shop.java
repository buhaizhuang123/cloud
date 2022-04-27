package com.cloud.shop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author haizhuangbu
 * @date 2022/4/27 11:47
 * @mark Shop
 */
@Data
public class Shop {

    // 机构编号
    private String shopId;

    // 主键id
    private String id;

    // 商户名称
    private String shopName;

    // 商户类型
    private String shopType;

    // 创建日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date crtTime;

    // 修改日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date mdfTime;

    // 商户地址
    private String shopAddr;

}
