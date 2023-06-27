package com.cloud.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloud.common.JwtUtils;
import com.cloud.common.Page;
import com.cloud.sys.service.FlowableService;
import io.jsonwebtoken.Claims;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/findAssignWaitTask")
    public JSONObject findAssignWaitTask(@RequestBody Page page, HttpServletRequest request) {
        String authentication = request.getHeader("authentication");
        Claims claims = JwtUtils.parseJwt(authentication);
        String usrename = (String)(claims.get("username"));
        RowBounds rowBounds = new RowBounds(page.getPageNum(),page.getPageSize());
        return flowableService.findAssignTask(usrename,rowBounds);
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
