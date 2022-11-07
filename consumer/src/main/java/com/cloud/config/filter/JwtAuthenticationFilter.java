package com.cloud.config.filter;

import com.cloud.common.JwtUtils;
import com.cloud.common.SpringContextUtils;
import com.cloud.config.handler.AuthenticationUnauthorizedHandler;
import com.cloud.config.handler.CustAuthFailHandler;
import com.cloud.config.handler.CustAuthSuccHandler;
import com.cloud.config.impl.UsernamePasswordAuth;
import com.cloud.sys.service.ProductService;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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

    @Autowired
    private AuthenticationSuccessHandler authenticationMeSuccessHandler;


    @Autowired
    private ProductService webScoketService;

    private Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        logger.info("=============== 执行非login身份验证 ======================");
        String token = request.getHeader("authentication");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("authentication".equals(cookie.getName())) {
                token = cookie.getValue();
            }
        }
        if (StringUtils.isBlank(token)) {
            UsernamePasswordAuth usernamePasswordAuth = new UsernamePasswordAuth(request.getParameter("username"), request.getParameter("password"));
            authenticationUnauthorizedHandler.onAuthenticationFailure(request, response, new BadCredentialsException("请登录"));
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
        webScoketService.sendAll(username + " : " + "token校验成功!!!");
//        authenticationMeSuccessHandler.onAuthenticationSuccess(request, response, auth);
        chain.doFilter(request, response);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        return request.getServletPath().equals("/login") || request.getServletPath().matches("^/image.*$") || request.getServletPath().matches("^/file.*$") || request.getServletPath().matches("^/sys.*$");
    }

}
