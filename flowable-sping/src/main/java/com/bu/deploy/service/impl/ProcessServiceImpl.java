package com.bu.deploy.service.impl;

import com.bu.deploy.dto.ProcessInstanceDto;
import com.bu.deploy.service.ProcessService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author haizhuangbu
 * @date 2023/10/31 17:38
 * @mark ProcessServiceImpl
 */
@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;


    @Override
    public void startProcess(String deployId) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("name", "bhz");
        param.put("age", "123");
        ProcessInstance start = runtimeService.createProcessInstanceBuilder()
                .processDefinitionId(deployId)
                .variables(param)
                .tenantId("bhz")
                .name("启动了")
                .start();
    }


    @Override
    public List<ProcessInstanceDto> listProcess() {
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery()
                .list();
        return list.stream().map(i -> {
            Map<String, Object> variables = runtimeService.getVariables(i.getId());
            ProcessInstanceDto processInstanceDto = new ProcessInstanceDto();
            BeanUtils.copyProperties(i, processInstanceDto);
            processInstanceDto.setVars(variables);
            return processInstanceDto;
        }).collect(Collectors.toList());
    }
}
