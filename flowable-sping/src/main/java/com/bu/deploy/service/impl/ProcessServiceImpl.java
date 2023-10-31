package com.bu.deploy.service.impl;

import com.bu.deploy.service.ProcessService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstanceBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author haizhuangbu
 * @date 2023/10/31 14:28
 * @mark ProcessServiceImpl
 */
@Service
public class ProcessServiceImpl implements ProcessService {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private RepositoryService repositoryService;


    /**
     * @param processName 流程名
     * @param processPath 流程路径
     */
    @Override
    public void createProcess(String processName, String processPath) {

        repositoryService.createDeployment().name(processName)
                .addClasspathResource("processes/" + processPath).deploy();


    }
}
