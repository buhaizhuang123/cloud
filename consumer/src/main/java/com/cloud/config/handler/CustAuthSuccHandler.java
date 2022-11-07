package com.cloud.config.handler;

import com.alibaba.fastjson.JSONObject;
import com.cloud.common.JwtUtils;
import com.cloud.sys.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author haizhuangbu
 * @date 2022/6/2 22:25
 * @mark CustAuthSuccHandler 登录成功逻辑
 */
@Service
public class CustAuthSuccHandler implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(CustAuthSuccHandler.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("================ 执行登录成功逻辑 ===================");
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        // 跳转
        User user = new User();
        user.setAuth(authentication);
        String jwt = JwtUtils.createJwt(user);
        Cookie cookie = new Cookie("authentication", jwt);
        cookie.setMaxAge(600);
        response.addCookie(cookie);
        response.setHeader("authentication", jwt);
        logger.info("============== 存储token =======================");
        ValueOperations<String, String> string = redisTemplate.opsForValue();
        string.set(jwt, "1", 60, TimeUnit.MINUTES);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("code", HttpStatus.OK);
        map.put("success", true);
        map.put("message", "loginSuccess");
        outputStream.print(JSONObject.toJSONString(map));
        outputStream.flush();
        outputStream.close();
    }
}
