package com.cloud.config.handler;

import com.cloud.common.JwtUtils;
import com.cloud.sys.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author haizhuangbu
 * @date 2022/6/2 22:25
 * @mark CustAuthSuccHandler 登录成功逻辑
 */
@Service
public class CustAuthSuccHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        // 跳转
        User user = new User();
        user.setAuth(authentication);
        String jwt = JwtUtils.createJwt(user);
        response.setHeader("authentication", jwt);
//        response.sendRedirect("/sys/str");
        System.out.println("登录成功" + jwt);
        response.sendRedirect("sys/str");
        outputStream.flush();
        outputStream.close();
    }
}
