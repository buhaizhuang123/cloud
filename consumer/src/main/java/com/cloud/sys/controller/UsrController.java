package com.cloud.sys.controller;

import com.cloud.common.InfoUtils;
import com.cloud.common.Page;
import com.cloud.sys.dto.User;
import com.cloud.sys.service.ProductService;
import com.cloud.sys.service.UserService;
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

}
