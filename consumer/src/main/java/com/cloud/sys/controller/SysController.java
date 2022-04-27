package com.cloud.sys.controller;

import com.cloud.sys.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "get",method = RequestMethod.GET)
    public String tsSys(){
        return productService.show();
    }

    @RequestMapping("list")
    public List<Object> listShops(){
        return productService.listShops();
    }

}
