package com.bu.deploy.dao;

import com.bu.deploy.dto.TaskDto;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/11/6 16:30
 * @mark TaskDtoMapper
 */
public interface TaskDtoMapper {

    List<TaskDto> listAll();

    List<TaskDto> history(String procInstId);

}
