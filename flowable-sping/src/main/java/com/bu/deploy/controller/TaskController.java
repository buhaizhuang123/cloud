package com.bu.deploy.controller;

import com.bu.deploy.dto.TaskDto;
import com.bu.deploy.service.TaskRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


    /**
     * @param id
     * @param name
     * @return 调单接口
     */
    @RequestMapping("/claim")
    public boolean claim(String id, String name) {
        return taskRunService.claim(id, name);
    }

    /**
     * task complate
     */
    @RequestMapping("/complete")
    public Boolean complete(String id) {
        return taskRunService.complete(id);
    }

    @RequestMapping("/list")
    public List<TaskDto> list(String id) {
        return taskRunService.list(id);
    }


}
