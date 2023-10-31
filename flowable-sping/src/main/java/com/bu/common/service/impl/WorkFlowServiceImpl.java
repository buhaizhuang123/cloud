package com.bu.common.service.impl;

import com.bu.common.service.WorkflowService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author haizhuangbu
 * @date 2023/8/25 17:07
 * @mark WorkFlowServiceImpl
 */
@Service
public class WorkFlowServiceImpl implements WorkflowService {

    @Autowired
    private RuntimeService runtimeService;


    @Override
    public void startProcess(String processId, Map<String, Object> param) {

        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processId);
        runtimeService.setVariable(processId, "name", param);
        Map<String, Object> processVariables = processInstance.getProcessVariables();
        System.out.println("process");

    }
}
