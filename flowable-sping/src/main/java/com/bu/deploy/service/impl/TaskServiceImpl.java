package com.bu.deploy.service.impl;

import com.bu.deploy.service.TaskRunService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author haizhuangbu
 * @date 2023/11/2 10:16
 * @mark TaskServiceImpl
 */
@Slf4j
@Service
public class TaskServiceImpl implements TaskRunService {


    @Autowired
    private TaskService taskService;

    public Boolean claim(String id, String name) {
        try {
            taskService.claim(id, name);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}