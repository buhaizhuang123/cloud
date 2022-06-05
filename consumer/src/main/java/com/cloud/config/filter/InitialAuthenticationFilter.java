package com.cloud.config.filter;

import com.alibaba.fastjson.JSON;
import com.cloud.common.JwtUtils;
import com.cloud.config.handler.CustAuthFailHandler;
import com.cloud.config.handler.CustAuthSuccHandler;
import com.cloud.config.impl.OptAuthentication;
import com.cloud.config.impl.UsernamePasswordAuth;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author haizhuangbu
 * @date 2022/6/4 13:50
 * @mark InitialAuthenticationFilter 最前身份验证
 */
@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustAuthSuccHandler custAuthSuccHandler;
    @Autowired
    private CustAuthFailHandler custAuthFailHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("init");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getHeader("code");
        Enumeration<String> headerNames = request.getHeaderNames();


        try {
// 若是不包含otp 则会用用户名密码验证
            if (code == null) {
                UsernamePasswordAuth usernamePasswordAuth = new UsernamePasswordAuth(username, password);

                Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuth);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuth);
                custAuthSuccHandler.onAuthenticationSuccess(request, response, usernamePasswordAuth);
            } else {
                Authentication optAuthentication = new OptAuthentication(username, code);
                optAuthentication = authenticationManager.authenticate(optAuthentication);
                HashMap<String, Object> map = new HashMap<>();
                map.put("username", username);
                SignatureAlgorithm hs256 = SignatureAlgorithm.HS256;
                String jwt = Jwts.builder()
                        .setId(String.valueOf(UUID.randomUUID().toString()))
                        // 签发者
                        .setIssuer("mr.bu")
                        .setClaims(map)
                        .setIssuedAt(new Date())
                        .signWith(hs256, "mySecret").compact();
                response.setHeader("authentication", jwt);
                chain.doFilter(request, response);
            }
        } catch (BadCredentialsException e) {
            custAuthFailHandler.onAuthenticationFailure(request, response, e);
        }


    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login"); // 仅对login路径应用拦截
    }
}
