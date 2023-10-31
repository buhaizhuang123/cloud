package com.bu.common.service;

import java.util.Map;

/**
 * @author haizhuangbu
 * @date 2023/8/25 17:05
 * @mark WorkflowService 工作流
 */
public interface WorkflowService {

    void startProcess(String processId, Map<String, Object> param);

}
