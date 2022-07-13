package com.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/7/12 09:51
 * @mark SysService
 */
@FeignClient("consumer")
public interface ConsumerService {

    @RequestMapping("sys/list")
    List<Object> listShop();

}
