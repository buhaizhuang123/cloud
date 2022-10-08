package com.bu.pay.controller;

import com.bu.config.dto.ZtbOrderDTO;
import com.bu.service.WeChatPayService;
import com.github.binarywang.wxpay.exception.WxPayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haizhuangbu
 * @date 2022/10/8 15:23
 * @mark PayController
 */
@RestController
@RequestMapping("order")
public class PayController {

    @Autowired
    private WeChatPayService weChatPayService;

    @RequestMapping(value = "pay", method = RequestMethod.POST)
    public String pay(@RequestBody ZtbOrderDTO ztbOrderDTO) throws WxPayException {
        return weChatPayService.unifiedOrder(ztbOrderDTO);
    }


    @RequestMapping("ask")
    public String payAsk() {
        System.out.println("weChatPayService = " + "支付成功");
        return "支付成功";
    }

}
