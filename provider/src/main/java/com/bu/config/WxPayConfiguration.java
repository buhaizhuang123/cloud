package com.bu.config;

import com.bu.config.dto.WxMaProperty;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author haizhuangbu
 * @date 2022/10/8 15:05
 * @mark WxPayConfiguration
 */
//@Configuration
//@ConditionalOnClass(WxPayService.class)
//@EnableConfigurationProperties(WxMaProperty.class)
public class WxPayConfiguration {

    @Autowired
    WxMaProperty wxMaProperty;

    private static Map<String, WxPayService> wxPayServices = new HashMap<>();

    /**
     * @Description 默认公众号appid
     * @author: ybwei
     * @Date: 2021/3/22 15:17
     */
    private String defaultAppid = null;

//    @PostConstruct
    public void init() {
        //1、赋值wxPayServices
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(StringUtils.trimToNull(wxMaProperty.getAppid()));
        payConfig.setMchId(StringUtils.trimToNull(wxMaProperty.getMchId()));
        payConfig.setMchKey(StringUtils.trimToNull(wxMaProperty.getKey()));
        payConfig.setKeyPath(StringUtils.trimToNull(wxMaProperty.getKeyPath()));
        // 可以指定是否使用沙箱环境
        payConfig.setUseSandboxEnv(false);

        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        wxPayServices.put(wxMaProperty.getAppid(), wxPayService);
    }


    /**
     * @Description 根据appid获取WxPayService
     * @Author ybwei
     * @Date 2021/3/22 15:01
     * @Param [appid]
     * @Return com.github.binarywang.wxpay.service.WxPayService
     * @Exception
     */
    public WxPayService getMaService(String appid) {
        //1、如果appid为空，默认首个公众号appid
        if (appid == null) {
            appid = defaultAppid;
        }
        //2、路由
        WxPayService wxService = wxPayServices.get(appid);
        if (wxService == null) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }
        return wxService;
    }


}
