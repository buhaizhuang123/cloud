package com.bu.service;

import com.bu.common.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/7/12 10:05
 * @mark ProductService
 */
@FeignClient("product")
public interface ProductService {

    @RequestMapping("/ps/list")
    List list(Page page);


}
