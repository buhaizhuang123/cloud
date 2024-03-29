package com.cloud.shop.dto;

import lombok.Data;

/**
 * @author haizhuangbu
 * @date 2022/4/27 16:14
 * @mark Page
 */
@Data
public class Page {

    private Integer pageNum = 0;

    private Integer size = 10;

    private Integer pageSize = 10;

}
