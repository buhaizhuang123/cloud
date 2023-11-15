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

    private String owner;
    private String assigneeUpdatedCount;
    private String originalAssignee;
    private String assignee;
    private String delegationState;
    private String parentTaskId;
    private String name;
    private String localizedName;
    private String description;
    private String localizedDescription;
    private String priority;
    private String createTime;
    private String dueDate;
    private String suspensionState;
    private String category;
    private String isIdentityLinksInitialized;
    private String taskIdentityLinkEntities;
    private String executionId;
    private String processInstanceId;
    private String processDefinitionId;
    private String taskDefinitionId;
    private String scopeId;
    private String subScopeId;
    private String scopeType;
    private String scopeDefinitionId;
    private String taskDefinitionKey;
    private String formKey;
    private String isDeleted;
    private String isCanceled;
    private String isCountEnabled;
    private String variableCount;
    private String identityLinkCount;
    private String subTaskCount;
    private String claimTime;
    private String tenantId;
    private String eventName;
    private String eventHandlerId;
    private String queryVariables;
    private String queryIdentityLinks;
    private String forcedUpdate;
    private String variableInstances;
    private String usedVariablesCache;
    private String transientVariabes;
    private String cachedElContext;
    private String id;
    private String revision;
    private String isInserted;
    private String isUpdated;
    private String AbstractEntity;
    private String originalPersistentState;
    private String rev;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date lastUpdatedTime;

    private Map<String, Object> params;
}
