package com.bu.deploy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author haizhuangbu
 * @date 2023/6/20 10:39
 * @mark BusDeployInfoDto
 */
@Data
public class BusDeployInfoDto {

    private Long id;

    private String deployName;

    private String busName;

    private String deployStatus;

    private String routerPath;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date modifyTime;

}
