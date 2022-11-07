package com.cloud.limit;

import com.cloud.sys.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/11/4 17:55
 * @mark LimitController
 */
@RequestMapping("limit")
@RestController
public class LimitController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "query", method = RequestMethod.GET)
    public Object find() {
        return productService.findLimit();
    }


}
