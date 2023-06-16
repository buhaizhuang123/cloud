package com.bu.pay.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.*;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author haizhuangbu
 * @date 2023/4/24 14:36
 * @mark AliPayController
 */
@RestController()
@RequestMapping("aliPay")
@ConfigurationProperties(prefix = "alipay")
@Data
public class AliPayController {

    private String url;

    private String appid;

    private String format;

    private String charset;

    private String signType;

    private String alipayPublicKey;

    private String alipayPrivateKey;

    @GetMapping("pay")
    public String pay() throws AlipayApiException {

        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setAppId(appid);
        alipayConfig.setFormat(format);
        alipayConfig.setServerUrl(url);
        alipayConfig.setCharset(charset);
        alipayConfig.setAlipayPublicKey(alipayPublicKey);
        alipayConfig.setSignType(signType);
        alipayConfig.setPrivateKey(alipayPrivateKey);
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        AlipayOpenPublicTemplateMessageIndustryModifyRequest request
                = new AlipayOpenPublicTemplateMessageIndustryModifyRequest();
        JSONObject bizContent = new JSONObject();

        String uuid = UUID.randomUUID().toString().replace("-", "");
        bizContent.put("out_trade_no", uuid);
        bizContent.put("total_amount", 5.00);
        bizContent.put("subject", "测试123");
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");

        request.setBizContent(bizContent.toJSONString());
        AlipayOpenPublicTemplateMessageIndustryModifyResponse execute = alipayClient.pageExecute(request);

        if (execute.isSuccess()) {
            return  execute.getBody();
        }
        return execute.getMsg();
    }


}
