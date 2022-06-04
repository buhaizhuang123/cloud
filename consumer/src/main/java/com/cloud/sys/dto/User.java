package com.cloud.sys.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author haizhuangbu
 * @date 2022/4/28 10:12
 * @mark User
 */
@Data
@NoArgsConstructor
public class User implements UserDetails {

    private String userId;

    private String userName;

    private String userPass;

    private String userEnable;

    private String userAuth;


    public void setAuth(Authentication authentication){
        this.userName = authentication.getName();
        this.userPass = (String) authentication.getCredentials();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String authStr = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        this.userAuth = authStr;
        this.userId = UUID.randomUUID().toString();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (this.userAuth != null) {
            String[] auths = this.userAuth.split(",");
            for (String auth : auths) {
                authorities.add(() -> auth);
            }

        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.userPass;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
