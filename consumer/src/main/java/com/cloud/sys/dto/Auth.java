package com.cloud.sys.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author haizhuangbu
 * @date 2022/6/1 21:23
 * @mark Auth
 */
@Data
public class Auth implements GrantedAuthority {

    private String usrAuthId;

    private String authRole;

    private String userName;

    @Override
    public String getAuthority() {
        return null;
    }
}
