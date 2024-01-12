package com.cloud.sys.service;

import com.alibaba.fastjson.JSONObject;
import com.cloud.common.Page;
import com.cloud.sys.dto.GroupEntity;
import com.cloud.sys.dto.UserEntity;
import com.cloud.sys.to.FlowableIdentityGroupInfo;
import com.cloud.sys.to.IdentityUserInfo;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    @PostMapping("/show/waitTask")
    JSONObject findAssignTask(@RequestParam("userId") String assign, @RequestBody RowBounds rowBounds);

    /**
     * @return 找到待处理工作流
     */
    @GetMapping("/show/listDeploys")
    JSONObject listDeploys(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);


    @PostMapping("/deploy/insertBusDeploy")
    JSONObject insertBusDeploy(@RequestBody HashMap<String, Object> map);


    @PostMapping("/deploy/listBusDeployInfo")
    JSONObject listBusDeployInfo(@RequestBody HashMap<String, Object> map, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);


    @GetMapping("/identity/saveGroup")
    void saveIdentityGroup(@RequestBody FlowableIdentityGroupInfo groupInfo);


    @GetMapping("/identity/saveUser")
    void saveIdentityUser(@RequestBody IdentityUserInfo identityUserInfo);

    @RequestMapping(value = "/identify/addUser", method = RequestMethod.POST)
    void addUserEntity(@RequestBody UserEntity userEntity);


    @RequestMapping(value = "/identify/addGroup", method = RequestMethod.POST)
    void addUserGroup(@RequestBody GroupEntity entity);


    @RequestMapping(value = "/identify/deleteUser", method = RequestMethod.POST)
    Boolean deleteUser(@RequestParam("userId") String userId);


    @RequestMapping(value = "/identify/listGroup", method = RequestMethod.POST)
    PageInfo<GroupEntity> listGroup(@RequestBody Page page);


    @RequestMapping(value = "/identify/deleteGroup", method = RequestMethod.POST)
    Boolean deleteGroup(@RequestParam("groupId") String groupId);


    @RequestMapping(value = "/identify/listGroupAll", method = RequestMethod.POST)
    List<GroupEntity> listGroupAll();
}
