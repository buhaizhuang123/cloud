package com.cloud.sys.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author haizhuangbu
 * @date 2022/6/2 22:01
 * @mark ErroEntityPonint
 */
public class ErrEntityPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setHeader("message", "I , M Love ");
        httpServletResponse.setHeader("error", e.getMessage());
        httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value());
    }
}
