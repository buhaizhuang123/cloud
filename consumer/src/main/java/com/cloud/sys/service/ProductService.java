package com.cloud.sys.service;

import com.alibaba.fastjson.JSONObject;
import com.cloud.common.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/4/27 11:24
 * @mark ProductService
 */
@FeignClient(name = "product")
public interface ProductService {

    @RequestMapping(value = "/shop/show", method = RequestMethod.GET)
    String show();

    @PostMapping("/shop/list")
    JSONObject listShops(@RequestBody RowBounds rowBounds);


    @PostMapping("/usr/list")
    List listUsr(@RequestBody Page page);

    /**
     * 发送数据到全部
     */
    @RequestMapping(value = "connect/sendAll", method = RequestMethod.GET)
    String sendAll(@RequestParam("message") String message);

    @RequestMapping("/ps/search")
    Object searchPs(Object object);

    @RequestMapping("/ps/list")
    Object listPs(Page page);

    @RequestMapping(value = "limit/query", method = RequestMethod.POST)
    Object findLimit(@RequestBody(required = false) Object o);

    @RequestMapping(value = "loanRever/list",method = RequestMethod.POST)
    List<Object> list(@RequestBody JSONObject loan, @RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum);

}
