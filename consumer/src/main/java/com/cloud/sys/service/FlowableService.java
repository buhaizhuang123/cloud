package com.cloud.sys.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * @author haizhuangbu
 * @date 2023/6/19 15:34
 * @mark FlowableService 工作流服务
 */
@FeignClient("flowable")
public interface FlowableService {


    /**
     * @return 找到待处理工作流
     */
    @GetMapping("/show/waitTask")
    JSONObject findAssignTask(@RequestParam("userId") String assign);

    /**
     * @return 找到待处理工作流
     */
    @GetMapping("/show/listDeploys")
    JSONObject listDeploys(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize);


    @PostMapping("/deploy/insertBusDeploy")
    JSONObject insertBusDeploy(@RequestBody HashMap<String, Object> map);



    @PostMapping("/deploy/listBusDeployInfo")
    JSONObject listBusDeployInfo(@RequestBody HashMap<String, Object> map,@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize);

}
