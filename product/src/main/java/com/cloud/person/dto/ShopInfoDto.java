package com.cloud.person.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

/**
 * @author haizhuangbu
 * @date 2024/1/17 16:05
 * @mark ShopInfoDto
 */
@Data
@Document(indexName = "shop",type = "_doc")
public class ShopInfoDto {

    @Id
    private String id;

    private String shopName;

    private Long price;

    private String sts;

    private Integer num;
}
