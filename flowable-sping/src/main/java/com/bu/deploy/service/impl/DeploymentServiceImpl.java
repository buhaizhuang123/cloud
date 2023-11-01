package com.bu.deploy.service.impl;

import com.bu.deploy.dto.DeploymentDto;
import com.bu.deploy.service.DeploymentService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.Deployment;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author haizhuangbu
 * @date 2023/10/31 14:28
 * @mark ProcessServiceImpl
 */
@Service
public class DeploymentServiceImpl implements DeploymentService {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private RepositoryService repositoryService;


    /**
     * @param processName 流程名
     * @param processPath 流程路径
     */
    @Override
    public void createDeployment(String processName, String processPath) {
        String tenantId = UUID.randomUUID().toString();
        repositoryService.createDeployment()
                .name(processName)
                // 租户
                .tenantId(tenantId)
                // 种类
                .category(processName)
                .deploymentProperty("name", processName)
                .deploymentProperty("path", processPath)
                .addClasspathResource("processes/" + processPath).deploy();
    }

    @Override
    public List<DeploymentDto> listDeployment() {
        List<Deployment> list = repositoryService.createDeploymentQuery()
                .list();
        List<DeploymentDto> collect = list.stream().map(i -> {
            DeploymentDto deploymentDto = new DeploymentDto();
            BeanUtils.copyProperties(i, deploymentDto);
            return deploymentDto;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public boolean deleteDeployment(String deployId) {
        repositoryService.deleteDeployment(deployId);
        return true;
    }
}
