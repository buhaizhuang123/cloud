package com.bu.deploy.service.impl;

import com.bu.deploy.dao.DeploymentDao;
import com.bu.deploy.dto.DeploymentDto;
import com.bu.deploy.service.DeploymentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
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
@Slf4j
@Service
public class DeploymentServiceImpl implements DeploymentService {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private DeploymentDao deploymentDao;

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
                // 工作流文件位置
                .addClasspathResource("processes/" + processPath).deploy();
    }

    @Override
    public PageInfo<DeploymentDto> listDeployment(RowBounds rowBounds) {
        PageHelper.startPage(rowBounds.getOffset(), rowBounds.getLimit());
        List<DeploymentDto> deploymentDtos = deploymentDao.listDeployment(null);
        PageInfo<DeploymentDto> deploymentDtoPageInfo = new PageInfo<>(deploymentDtos);
        return deploymentDtoPageInfo;
    }

    @Override
    public boolean deleteDeployment(String deployId) {
        repositoryService.deleteDeployment(deployId);
        return true;
    }

    /**
     * @param id
     * @return 中断
     */
    @Override
    public boolean suspendDeployment(String id) {
        try {
            repositoryService.suspendProcessDefinitionById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param id
     * @return 唤醒
     */
    @Override
    public boolean activeDeployment(String id) {
        try {
            repositoryService.activateProcessDefinitionById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
