package com.cloud.person;

import com.cloud.common.Page;
import com.cloud.sys.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haizhuangbu
 * @date 2022/6/8 10:11
 * @mark PsController
 */
@RequestMapping("ps")
@RestController
public class PsController {

    @Autowired
    private ProductService productService;

    @RequestMapping("list")
    public Object psList(Page page) {
        return productService.listPs(page);
    }

}
