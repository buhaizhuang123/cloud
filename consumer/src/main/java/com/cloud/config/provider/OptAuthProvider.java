package com.cloud.config.provider;

import com.cloud.config.AuthServerProxy;
import com.cloud.config.impl.OptAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author haizhuangbu
 * @date 2022/6/3 13:40
 * @mark OptAuthProvider
 */
@Component
public class OptAuthProvider implements AuthenticationProvider {

    @Autowired
    protected AuthServerProxy authServerProxy;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        Boolean result = authServerProxy.sendOTP(username, password);
        if (result) {
            return new OptAuthentication(username, password);
        } else {
            throw new BadCredentialsException("Bad credentials.");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return OptAuthentication.class.isAssignableFrom(aClass);
    }
}
