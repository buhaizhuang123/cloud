package com.cloud.config.filter;

import com.cloud.common.JwtUtils;
import com.cloud.config.handler.CustAuthSuccHandler;
import com.cloud.config.impl.UsernamePasswordAuth;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("jwt");
        String jwt = request.getHeader("authentication");
        String username1 = request.getParameter("username");
        System.out.println(username1);
        if (StringUtils.isBlank(jwt)) {
//            chain.doFilter(request, response);
//            response.sendRedirect("/login");
            UsernamePasswordAuth usernamePasswordAuth = new UsernamePasswordAuth(request.getParameter("username"), request.getParameter("password"));
            custAuthSuccHandler.onAuthenticationSuccess(request, response, usernamePasswordAuth);

            return;
        }

        Claims claims = JwtUtils.parseJwt(jwt);
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
