package com.bu.auto;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/**
 * @author haizhuangbu
 * @date 2023/6/15 20:09
 * @mark CamundaApplication
 */
@EnableProcessApplication
@Configuration
public class CamundaApplication {

    @Autowired
    private RuntimeService runtimeService;

    @EventListener
    public void processPostDeploy(PostDeployEvent event) {
        runtimeService.startProcessInstanceByKey("Process_00tkbmc");
    }


}
