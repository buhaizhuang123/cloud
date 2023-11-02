package com.bu.deploy.service;

import com.bu.deploy.dto.DeploymentDto;
import org.flowable.engine.repository.Deployment;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/10/31 14:28
 * @mark ProcessService
 */
public interface DeploymentService {


    void createDeployment(String processName, String processPath);

    List<DeploymentDto> listDeployment();

    boolean deleteDeployment(String deployId);

    boolean suspendDeployment(String id);

    boolean activeDeployment(String id);
}
