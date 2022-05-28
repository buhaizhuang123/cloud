package com.cloud.person.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author haizhuangbu
 * @date 2022/5/28 10:13
 * @mark PersonDto
 */
@Data
public class PersonDto {

    private String address;

    private String birthday;

    private String idCard;

    private String name;

    private Integer age;
}
