package com.bu.Fs;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.bu.common.Page;
import com.bu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/7/12 10:15
 * @mark FsController
 */
@RestController
@RequestMapping("fs")
public class FsController {
    @Autowired
    private ProductService productService;

    @RequestMapping("list")
    @SentinelResource(value = "fs-list", blockHandler = "exceptionHandler", fallback = "rollback")
    public List list(Page page) {
        return productService.list(page);
    }

    public List exceptionHandler(Page page, BlockException exception) {
        exception.printStackTrace();
        ArrayList<Object> objects = new ArrayList<>();
        objects.add("系统繁忙,稍后重试！！！");
        return objects;
    }


    public List rollback(Page page) {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add("系统繁忙,稍后重试！！！");
        return objects;
    }

}
