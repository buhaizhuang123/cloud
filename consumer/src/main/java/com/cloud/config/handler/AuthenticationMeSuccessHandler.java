package com.cloud.config.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author haizhuangbu
 * @date 2022/10/26 20:39
 * @mark AuthenticationSuccessHandler
 */
@Component
public class AuthenticationMeSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
        PrintWriter writer = response.getWriter();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("code", HttpStatus.OK.value());
        map.put("message", "loginSucc");
        writer.print(JSONObject.toJSONString(map));
        writer.flush();
        writer.close();
    }
}
