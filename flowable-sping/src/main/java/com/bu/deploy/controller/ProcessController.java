package com.bu.deploy.controller;

import com.bu.deploy.dto.ProcessInstanceDto;
import com.bu.deploy.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/10/31 17:33
 * @mark ProcessController
 */

@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @RequestMapping("/start")
    public void startProcess(String deployId) {
        processService.startProcess(deployId);
    }


    @RequestMapping("/list")
    public List<ProcessInstanceDto> listProcess(Integer pageNum, Integer pageSize) {
        return processService.listProcess(pageNum,pageSize);
    }


}
