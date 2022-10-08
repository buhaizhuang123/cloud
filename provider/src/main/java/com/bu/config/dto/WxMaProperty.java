package com.bu.config.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author haizhuangbu
 * @date 2022/10/8 15:04
 * @mark WxMaProperty
 */
@Data
@ConfigurationProperties(prefix = "wx.pay")
public class WxMaProperty {


    /**
     * 公众账号ID
     */
    private String appid;

    /**
     * 设置微信小程序的Secret
     */
    private String mchId;

    /**
     * 支付通知地址
     */
    private String notifyUrl;

    /**
     * 退款通知地址
     */
    private String refundUrl;

    /**
     * 支付key
     */
    private String key;

    /**
     * 支付key
     */
    private String keyPath;

}
