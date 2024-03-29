package com.cloud.config.handler;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author haizhuangbu
 * @date 2022/6/2 22:25
 * @mark CustAuthSuccHandler 登录失败逻辑
 */
@Service
public class CustAuthFailHandler implements AuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(CustAuthFailHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        PrintWriter writer = response.getWriter();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
        map.put("success", false);
        map.put("message", e.getMessage());
        writer.write(JSONObject.toJSONString(map));
    }
}
