package com.bu.deploy.controller;

import com.bu.deploy.service.ProcessService;
import liquibase.pro.packaged.E;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haizhuangbu
 * @date 2023/10/31 14:26
 * @mark ProcessController
 */
@RestController
@RequestMapping("/process")
public class ProcessController {

    private Logger log = LoggerFactory.getLogger(ProcessController.class);


    @Autowired
    private ProcessService processService;

    @RequestMapping("/create")
    public boolean createProcess(@RequestParam("name") String name, @RequestParam("filePath") String fileName) {

        try {
            processService.createProcess(name, fileName);
        } catch (Exception e) {
            log.error(" 流程新增失败:{} ", e);
            return false;
        }
        return true;
    }


}
