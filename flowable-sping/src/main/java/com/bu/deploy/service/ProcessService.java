package com.bu.deploy.service;

import com.bu.deploy.dto.ProcessInstanceDto;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/10/31 17:37
 * @mark ProcessService
 */
public interface ProcessService {

    void startProcess(String deployId);

    List<ProcessInstanceDto> listProcess(Integer pageNum, Integer pageSize);
}
