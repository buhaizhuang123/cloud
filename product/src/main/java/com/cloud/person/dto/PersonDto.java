package com.cloud.person.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author haizhuangbu
 * @date 2022/5/28 10:13
 * @mark PersonDto
 */
@Data
public class PersonDto implements Serializable {


    private static final long serialVersionUID = -8110455352964605377L;
    @JSONField(name = "_id")
    private String id;

    private String address;

    private String birthday;

    @JSONField(name = "id_card")
    private String idCard;

    private String name;

    private Integer age;
}
