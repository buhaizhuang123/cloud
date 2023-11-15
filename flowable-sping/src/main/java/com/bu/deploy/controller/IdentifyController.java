package com.bu.deploy.controller;

import com.bu.deploy.dto.GroupEntity;
import com.bu.deploy.dto.UserEntity;
import com.bu.deploy.service.IdentifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/11/14 10:28
 * @mark IdentityController
 */
@RequestMapping("/identify")
@RestController
public class IdentifyController {

    @Autowired
    private IdentifyService identifyService;


    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public Boolean addUser(@RequestBody UserEntity userEntity) {
        identifyService.addUser(userEntity);
        return true;
    }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<UserEntity> listUser(Integer pageNum, Integer pageSize) {
        return identifyService.list(pageNum, pageSize);
    }

    @RequestMapping(value = "addGroup", method = RequestMethod.POST)
    public Boolean addGroup(@RequestBody GroupEntity groupEntity) {
        identifyService.addGroup(groupEntity);
        return true;
    }


    @RequestMapping("/relation")
    public Boolean relationGroup(String userId, String groupId) {
        identifyService.relationGroup(userId, groupId);
        return true;
    }


    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public Boolean removeUserRelation(String userId) {
        return identifyService.removeUserRelation(userId);
    }

}
