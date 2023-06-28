package com.cloud.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author haizhuangbu
 * @date 2023/6/27 21:03
 * @mark FlowableService
 */
@FeignClient("flowable")
public interface FlowableService {

    @PostMapping("show/startProcess")
    JSONObject startProcess(@RequestParam("processId") String processId, @RequestBody(required = false) Map<String,Object> map);

    @RequestMapping("deploy/findBusDeployInfo")
    JSONObject findBusDeployInfo(@RequestParam("busName") String busName);
}
