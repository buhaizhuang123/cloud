package com.cloud.sys.controller;

import com.cloud.common.InfoUtils;
import com.cloud.common.Page;
import com.cloud.sys.dto.GroupEntity;
import com.cloud.sys.dto.User;
import com.cloud.sys.dto.UserEntity;
import com.cloud.sys.service.FlowableService;
import com.cloud.sys.service.ProductService;
import com.cloud.sys.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author haizhuangbu
 * @date 2022/4/29 09:53
 * @mark UsrController
 */
@RequestMapping("usr")
@RestController
public class UsrController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private FlowableService flowableService;

    @PostMapping("list")
    public List listUsr(@RequestBody(required = false) Page page) {
        return productService.listUsr(page);
    }


    @RequestMapping("info")
    public String getRt() {
        String info = InfoUtils.pull("usr");
        return info;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestBody User user) {
        userService.addUser(user);
        return "新增成功";
    }

    @RequestMapping(value = "/addUsr", method = RequestMethod.POST)
    public Boolean addUserEntity(@RequestBody UserEntity entity) {
        userService.addUserEntity(entity);
        return true;
    }


    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public Boolean addUsrGroup(@RequestBody GroupEntity entity) {
        userService.addUserGroup(entity);
        return true;
    }


    @RequestMapping(value = "/listUser", method = RequestMethod.POST)
    public PageInfo<User> listUser(@RequestBody Page page) {
        return userService.listUser(page.getPageNum(), page.getPageSize());
    }


    @PostMapping("/deleteUser")
    public Integer deleteUser(String userId) {
        return userService.deleteUser(userId);
    }

    @RequestMapping(value = "/listGroup", method = RequestMethod.POST)
    public PageInfo<GroupEntity> listGroup(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        Page page = new Page();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        return flowableService.listGroup(page);
    }

    @RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
    public Boolean deleteGroup(@RequestParam String groupId) {
        return flowableService.deleteGroup(groupId);
    }

    @RequestMapping(value = "/listGroupAll", method = RequestMethod.POST)
    public List<GroupEntity> listGroup() {
        return flowableService.listGroupAll();
    }

}
