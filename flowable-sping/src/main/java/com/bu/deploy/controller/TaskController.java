package com.bu.deploy.controller;

import com.bu.deploy.service.TaskRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haizhuangbu
 * @date 2023/11/2 10:14
 * @mark TaskController
 */

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRunService taskRunService;


    @RequestMapping("/claim")
    public boolean claim(String id, String name) {
        return taskRunService.claim(id, name);
    }


}
