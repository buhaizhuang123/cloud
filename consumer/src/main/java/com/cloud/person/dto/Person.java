package com.cloud.person.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author haizhuangbu
 * @date 2022/6/9 10:55
 * @mark Person
 */
@Data
public class Person {

    private Integer id;

    @NotBlank(message = "yichang")
    @JsonProperty("PERSON_NAME")
    @JsonAlias("name")
    private String name;


    @JsonProperty("PERSON_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonAlias("date")
    private Date date;
}
