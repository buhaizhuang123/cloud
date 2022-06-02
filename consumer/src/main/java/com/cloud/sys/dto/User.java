package com.cloud.sys.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/4/28 10:12
 * @mark User
 */
@Data
public class User implements UserDetails {

    private String userId;

    private String userName;

    private String userPass;

    private String userEnable;

    private String userAuth;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = Arrays.asList();
        if (this.userAuth != null) {
            String[] auths = this.userAuth.split(",");
            for (String auth : auths) {
                GrantedAuthority grantedAuthority = new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return auth;
                    }
                };
                authorities.add(grantedAuthority);
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
