package com.cloud.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloud.common.Page;
import com.cloud.common.Result;
import com.cloud.sys.dao.UserMapper;
import com.cloud.sys.dto.User;
import com.cloud.sys.service.ProductService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    public Result findUser(Page page) {
        RowBounds rowBounds = new RowBounds(page.getPageNum(), page.getPageSize());
        List<User> user = userMapper.findUser(rowBounds);
        Result<List<User>> userResult = new Result<>();
        userResult.success(user);
        return userResult;
    }

    @RequestMapping(value = "list",method = RequestMethod.POST)
    public List<Object> list(@RequestBody JSONObject loan, @RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum){
        List<Object> list = productService.list(loan, pageSize, pageNum);
        return list;
    }

}
