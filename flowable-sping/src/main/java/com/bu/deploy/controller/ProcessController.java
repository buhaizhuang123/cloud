package com.bu.deploy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bu.deploy.dto.DeploymentDto;
import com.bu.deploy.dto.ProcessInstanceDto;
import com.bu.deploy.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @PostMapping("/start")
    public void startProcess(@RequestParam String deployId, @RequestBody Object param) {
        processService.startProcess(deployId, param);
    }


    @RequestMapping("/list")
    public List<ProcessInstanceDto> listProcess(Integer pageNum, Integer pageSize) {
        return processService.listProcess(pageNum, pageSize);
    }


}
