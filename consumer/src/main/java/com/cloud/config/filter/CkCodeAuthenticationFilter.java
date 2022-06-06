package com.cloud.config.filter;

import com.cloud.config.handler.CkCodeFailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author haizhuangbu
 * @date 2022/6/6 18:18
 * @mark CkCodeAuthenticationFilter
 */
@Component
public class CkCodeAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private CkCodeFailHandler ckCodeFailHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        logger.info("================== 执行验证码校验 =======================");
        String key = request.getParameter("imgKey");
        String value = request.getParameter("imgValue");
        ValueOperations<String, String> str = redisTemplate.opsForValue();
        String code = str.get(key);
        assert code != null;
        if (!code.equalsIgnoreCase(value)) {
            ckCodeFailHandler.onAuthenticationFailure(request, response, new BadCredentialsException("验证码校验错误"));
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
