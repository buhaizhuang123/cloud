package com.cloud.dict.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author haizhuangbu
 * @date 2022/10/26 20:21
 * @mark Dict
 */
@Data
public class Dict {

    private String dictId;

    private String dictType;

    private String dictName;

    private String dictValue;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    private String isDel;

}
