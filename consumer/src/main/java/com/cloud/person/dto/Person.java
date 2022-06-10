package com.cloud.person.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author haizhuangbu
 * @date 2022/6/9 10:55
 * @mark Person
 */
@Data
public class Person {

    @NotBlank(message = "yichang")
    private String name;

}
