package com.bu.deploy.service.impl;

import com.bu.deploy.dao.TaskDtoMapper;
import com.bu.deploy.dto.TaskDto;
import com.bu.deploy.service.TaskRunService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.HistoryService;
import org.flowable.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private HistoryService historyService;

    @Autowired
    private TaskDtoMapper taskDtoMapper;

    public Boolean claim(String id, String name) {
        try {
            // 取消调单
            taskService.unclaim(id);
            // 调单
            taskService.claim(id, name);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public Boolean complete(String id, String commonContext) {
        try {
            // 审批意见
            HashMap<String, Object> param = new HashMap<>();
            param.put("commonContext", commonContext);
            taskService.complete(id, param);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<TaskDto> list(String id) {
        List<TaskDto> taskDtos = taskDtoMapper.listAll();
        return taskDtos;
    }

    @Override
    public PageInfo<TaskDto> list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TaskDto> taskDtos = taskDtoMapper.listAll();
        taskDtos
                .forEach(i -> {
                    if (i.getBytes() != null) {
                        Map<String, Object> variables = taskService.getVariables(i.getId());
                        i.setJsonVariable((HashMap) variables.get("variable"));
                    }

                });
        PageInfo<TaskDto> taskDtoPageInfo = new PageInfo<>(taskDtos);
        return taskDtoPageInfo;
    }

    @Override
    public List<TaskDto> history(String procInstId) {
        List<TaskDto> history = taskDtoMapper.history(procInstId);
        return history;
    }
}