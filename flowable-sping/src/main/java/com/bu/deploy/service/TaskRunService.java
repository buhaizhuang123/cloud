package com.bu.deploy.service;

import com.bu.deploy.dto.TaskDto;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/11/2 10:16
 * @mark TaskService
 */
public interface TaskRunService {
    Boolean claim(String id, String name);

    Boolean complete(String id);

    List<TaskDto> list(String id);

}