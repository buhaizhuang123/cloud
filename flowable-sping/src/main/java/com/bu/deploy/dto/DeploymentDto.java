package com.bu.deploy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author haizhuangbu
 * @date 2023/10/31 15:12
 * @mark DeploymentDto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeploymentDto {

    private String id;

    private String name;

    private String category;

    private String key;

    private String tenantId;

    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss", timezone = "GMT+8")
    private Date deploymentTime;

    private String xmlName;

    private String fileName;

    private String state;

    private String deployId;
}
