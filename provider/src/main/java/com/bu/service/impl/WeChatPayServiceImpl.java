package com.bu.service.impl;

import com.bu.config.WxPayConfiguration;
import com.bu.config.dto.WxMaProperty;
import com.bu.config.dto.ZtbOrderDTO;
import com.bu.service.WeChatPayService;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author haizhuangbu
 * @date 2022/10/8 15:10
 * @mark WeChatPayServiceImpl
 */
@Slf4j
@Service
public class WeChatPayServiceImpl implements WeChatPayService {


    @Resource
    private WxMaProperty wxMaProperty;

    @Resource
    private WxPayConfiguration wxPayConfiguration;

    /**
     * 统一下单
     *
     * @return
     * @throws Exception
     */
    @Override
    public String unifiedOrder(ZtbOrderDTO orderDTO) throws WxPayException {
        String spbillCreateIp = "127.0.0.1"; // WXPayUtil.getLocalIp()
        WxPayUnifiedOrderRequest orderRequestequest = WxPayUnifiedOrderRequest.newBuilder()
                .body(orderDTO.getCourseName())//订单名称
                .outTradeNo(orderDTO.getOrderNo())//商户的订单号
                .totalFee(orderDTO.getPrice().multiply(new BigDecimal("100")).intValue())//需要支付的金额（单位：分）
                .spbillCreateIp(spbillCreateIp)//APP和网页支付提交用户端ip
                .notifyUrl(wxMaProperty.getNotifyUrl())//支付结果回调地址
                .tradeType("NATIVE")//支付类型：JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
                .productId(String.valueOf(orderDTO.getId()))//支付的商品id（商户自定义）
                .build();
        //获取指定appId的WxPayService（com.github.binarywang.wxpay.service.WxPayService）调用统一下单
        WxPayUnifiedOrderResult result = wxPayConfiguration.getMaService(wxMaProperty.getAppid()).unifiedOrder(orderRequestequest);
        //返回的是微信的支付链接（需要转成二维码返给前端）
        return result.getCodeURL();
    }

    /**
     * 支付回调
     *
     * @param xmlData
     */
    @Override
    @Transactional
    public String payNotifywx(String xmlData) throws Exception {
        log.info("PayController parseOrderNotifyResult xmlData:{}", xmlData);
        //返回结果
        WxPayOrderNotifyResult notifyResult = wxPayConfiguration.getMaService(wxMaProperty.getAppid()).parseOrderNotifyResult(xmlData);
        //处理订单业务
        //通知微信订单处理成功
        return WxPayNotifyResponse.success("成功");
    }

}
