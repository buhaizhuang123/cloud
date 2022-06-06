package com.cloud.config.filter;

import com.cloud.common.JwtUtils;
import com.cloud.config.handler.AuthenticationUnauthorizedHandler;
import com.cloud.config.handler.CustAuthFailHandler;
import com.cloud.config.handler.CustAuthSuccHandler;
import com.cloud.config.impl.UsernamePasswordAuth;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/6/4 14:04
 * @mark JwtAuthenticationFilter
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustAuthSuccHandler custAuthSuccHandler;
    @Autowired
    private CustAuthFailHandler custAuthFailHandler;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AuthenticationUnauthorizedHandler authenticationUnauthorizedHandler;


    private Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        logger.info("=============== 执行非login身份验证 ======================");
        String token = request.getHeader("authentication");
        if (StringUtils.isBlank(token)) {
            UsernamePasswordAuth usernamePasswordAuth = new UsernamePasswordAuth(request.getParameter("username"), request.getParameter("password"));
            custAuthSuccHandler.onAuthenticationSuccess(request, response, usernamePasswordAuth);
            return;
        }
        ValueOperations<String, String> str = redisTemplate.opsForValue();
        String res = str.get(token);
        if (StringUtils.isBlank(res)) {
            authenticationUnauthorizedHandler.onAuthenticationFailure(request, response, new BadCredentialsException("token 失效, 请重新登录"));
            return;
        }
        Claims claims = JwtUtils.parseJwt(token);
        String username = claims.get("username").toString();
        GrantedAuthority a = new SimpleGrantedAuthority("user");
        UsernamePasswordAuth auth = new UsernamePasswordAuth(username, null, Arrays.asList(a));
        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(request, response);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/login");
    }
}
