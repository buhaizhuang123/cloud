package com.cloud.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author haizhuangbu
 * @date 2023/6/27 21:03
 * @mark FlowableService
 */
@FeignClient("flowable")
public interface FlowableService {

    @GetMapping("show/startProcess")
    JSONObject startProcess(@RequestParam String processId, @RequestBody(required = false) Map<String,Object> map);


}
