package com.bu.deploy.dao;

import com.bu.deploy.dto.DeploymentDto;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/11/1 13:53
 * @mark DeploymentDao
 */
public interface DeploymentDao {


    List<DeploymentDto> listDeployment(DeploymentDto deploymentDto);

}
