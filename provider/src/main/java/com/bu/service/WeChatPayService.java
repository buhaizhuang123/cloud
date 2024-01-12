package com.bu.service;

import com.bu.config.dto.ZtbOrderDTO;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * @author haizhuangbu
 * @date 2022/10/8 15:11
 * @mark WeChatPayService
 */
public interface WeChatPayService {

    String unifiedOrder(ZtbOrderDTO orderDTO) throws WxPayException;

    String payNotifywx(String xmlData) throws Exception;
}
