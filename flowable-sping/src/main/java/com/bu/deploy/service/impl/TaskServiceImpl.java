package com.bu.deploy.service.impl;

import com.bu.deploy.dto.TaskDto;
import com.bu.deploy.service.TaskRunService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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


    public Boolean complete(String id) {
        try {
            taskService.complete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<TaskDto> list(String id) {
        List<Task> list = taskService.createTaskQuery().processDefinitionId(id)
                .list();
        List<TaskDto> taskDtos = list.stream().map(i -> {
            TaskDto taskDto = new TaskDto();
            BeanUtils.copyProperties(i, taskDto);
            return taskDto;
        }).collect(Collectors.toList());
        return taskDtos;
    }

}