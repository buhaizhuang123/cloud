package com.bu.deploy.service;

import com.bu.deploy.dto.TaskDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/11/2 10:16
 * @mark TaskService
 */
public interface TaskRunService {
    Boolean claim(String id, String name);

    Boolean complete(String id, String commonContext);

    List<TaskDto> list(String id);

    PageInfo<TaskDto> list(Integer pageNum, Integer pageSize);

    List<TaskDto> history(String id);
}
