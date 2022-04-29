package com.cloud.sys.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/4/27 11:24
 * @mark ProductService
 */
@FeignClient(name = "product")
public interface ProductService {

    @RequestMapping(value = "/shop/show",method = RequestMethod.GET)
    String show();

    @RequestMapping("/shop/list")
    List<Object> listShops();


    @GetMapping("/usr/list")
    List listUsr();

}
