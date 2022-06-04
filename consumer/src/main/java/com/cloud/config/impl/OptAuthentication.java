package com.cloud.config.impl;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author haizhuangbu
 * @date 2022/6/3 13:22
 * @mark OptAuthentication
 */
public class OptAuthentication extends UsernamePasswordAuthenticationToken {
    public OptAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public OptAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
