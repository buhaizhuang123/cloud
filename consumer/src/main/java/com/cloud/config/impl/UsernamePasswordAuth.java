package com.cloud.config.impl;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author haizhuangbu
 * @date 2022/6/3 13:18
 * @mark UsernamePasswordAuth
 */
public class UsernamePasswordAuth extends UsernamePasswordAuthenticationToken {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UsernamePasswordAuth(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public UsernamePasswordAuth(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

}
