package com.bu.deploy.controller;

import com.bu.deploy.dto.DeploymentDto;
import com.bu.deploy.service.DeploymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/10/31 14:26
 * @mark ProcessController
 */
@RestController
@RequestMapping("/deployment")
public class DeploymentController {

    private Logger log = LoggerFactory.getLogger(DeploymentController.class);


    @Autowired
    private DeploymentService deploymentService;

    @RequestMapping("/create")
    public boolean createDeployment(@RequestParam("name") String name, @RequestParam("filePath") String fileName) {

        try {
            deploymentService.createDeployment(name, fileName);
        } catch (Exception e) {
            log.error(" 流程新增失败:{} ", e);
            return false;
        }
        return true;
    }


    @RequestMapping("/list")
    @ResponseBody
    public List<DeploymentDto> listDeployment() {
        List<DeploymentDto> deployments = deploymentService.listDeployment();
        return deployments;
    }

    @DeleteMapping("/delete")
    public boolean deleteDeployment(String id) {
        return deploymentService.deleteDeployment(id);
    }

    @RequestMapping("/suspend")
    public boolean suspend(String id) {
        return deploymentService.suspendDeployment(id);
    }


    @RequestMapping("/active")
    public boolean active(String id) {
        return deploymentService.activeDeployment(id);
    }

}
