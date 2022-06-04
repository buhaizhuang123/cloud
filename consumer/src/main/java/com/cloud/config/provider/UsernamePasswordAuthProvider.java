package com.cloud.config.provider;

import com.cloud.config.AuthServerProxy;
import com.cloud.config.impl.UsernamePasswordAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.security.AuthProvider;

/**
 * @author haizhuangbu
 * @date 2022/6/4 13:40
 * @mark UsernamePasswordAuthProvider 用户信息验证服务器
 */
@Component
public class UsernamePasswordAuthProvider implements AuthenticationProvider {
    @Autowired
    private AuthServerProxy authServerProxy;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 1.使用代理服务器调用身份认证服务器
        authServerProxy.sendAuth(authentication.getName(), authentication.getCredentials().toString());
        // 2.将原数据返回
        return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials().toString());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuth.class.isAssignableFrom(aClass);
    }
}
