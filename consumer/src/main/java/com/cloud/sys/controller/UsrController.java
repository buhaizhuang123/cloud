package com.cloud.sys.controller;

import com.cloud.sys.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping("list")
    public List listUsr(){
        return productService.listUsr();
    }

}
