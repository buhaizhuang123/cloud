package com.cloud.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloud.sys.service.FlowableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author haizhuangbu
 * @date 2023/6/19 15:38
 * @mark FlowableController
 */
@RequestMapping("/flowable")
@RestController
public class FlowableController {

    @Autowired
    private FlowableService flowableService;

    @GetMapping("/findAssignWaitTask")
    public JSONObject findAssignWaitTask(String assign) {
        return flowableService.findAssignTask(assign);
    }


    @PostMapping("/insertBusDeployInfo")
    public JSONObject insertBusDeployInfo(@RequestBody HashMap<String, Object> map) {
        return flowableService.insertBusDeploy(map);
    }


    @PostMapping("/listBusDeployInfo")
    public JSONObject listBusDeployInfo(@RequestBody HashMap<String, Object> map,Integer pageNum,Integer pageSize) {
        return flowableService.listBusDeployInfo(map,pageNum,pageSize);
    }


    @GetMapping("listDeploys")
    public JSONObject listDeploys(int pageNum, int pageSize){
        return flowableService.listDeploys(pageNum, pageSize);
    }

}
