package com.cloud.sys.controller;

import com.cloud.sys.dao.UserMapper;
import com.cloud.sys.dto.User;
import com.cloud.sys.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/4/27 11:03
 * @mark SysController
 */
@RequestMapping("sys")
@RestController
public class SysController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public String tsSys() {
        return productService.show();
    }

    @RequestMapping("list")
    public List<Object> listShops() {
        return productService.listShops();
    }

    @RequestMapping("str")
    public Authentication retStr() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        return authentication;
    }

    @RequestMapping("listUsr")
    public List<User> findUser() {
        return userMapper.findUser();
    }

}
