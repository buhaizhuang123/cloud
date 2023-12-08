package com.bu.deploy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @author haizhuangbu
 * @date 2023/11/2 16:24
 * @mark TaskDto
 */
@Data
public class TaskDto {

    private String id;


    private String executionId;


    private String processInstanceId;


    private String processDefinitionId;


    private String name;


    private String description;


    private String assignee;


    private String owner;


    private String tenantId;


    private String rev;


    private String bytes;

    private Map jsonVariable;

}
