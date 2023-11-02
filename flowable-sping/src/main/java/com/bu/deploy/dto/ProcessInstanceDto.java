package com.bu.deploy.dto;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @author haizhuangbu
 * @date 2023/11/1 16:44
 * @mark ProcessInstanceDto
 */
@Data
public class ProcessInstanceDto {
    private String currentFlowElement;
    private String currentListener;
    private String processInstance;
    private String parent;
    private String executions;
    private String superExecution;
    private String subProcessInstance;
    private String tenantId;
    private String name;
    private String description;
    private String localizedName;
    private String localizedDescription;
    private Date lockTime;
    private Boolean isActive;
    private Boolean isScope;
    private Boolean isConcurrent;
    private Boolean isEnded;
    private Boolean isEventScope;
    private Boolean isMultiInstanceRoot;
    private Boolean isCountEnabled;
    private String eventName;
    private String eventSubscriptions;
    private String jobs;
    private String timerJobs;
    private String tasks;
    private String identityLinks;
    private String deleteReason;
    private String suspensionState;
    private String startActivityId;
    private String startUserId;
    private Date startTime;
    private String eventSubscriptionCount;
    private String taskCount;
    private String jobCount;
    private String timerJobCount;
    private String suspendedJobCount;
    private String deadLetterJobCount;
    private String variableCount;
    private String identityLinkCount;
    private String processDefinitionId;
    private String processDefinitionKey;
    private String processDefinitionName;
    private String processDefinitionVersion;
    private String deploymentId;
    private String activityId;
    private String activityName;
    private String processInstanceId;
    private String businessKey;
    private String parentId;
    private String superExecutionId;
    private String rootProcessInstanceId;
    private String rootProcessInstance;
    private String forcedUpdate;
    private String queryVariables;
    private String callbackId;
    private String callbackType;
    private String variableInstances;
    private String usedVariablesCache;
    private String transientVariabes;
    private String cachedElContext;
    private String id;
    private String revision;
    private String isInserted;
    private String isUpdated;
    private String isDeleted;
    private String originalPersistentState;
}
