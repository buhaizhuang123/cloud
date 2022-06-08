package com.cloud.person.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author haizhuangbu
 * @date 2022/5/28 10:13
 * @mark PersonDto
 */
@Data
public class PersonVo extends PersonDto {

    private String type;

    private String value;

}
