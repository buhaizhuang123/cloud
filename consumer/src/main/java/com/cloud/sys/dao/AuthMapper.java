package com.cloud.sys.dao;

import com.cloud.sys.dto.Auth;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/6/1 21:20
 * @mark AuthMapper
 */
public interface AuthMapper {


    Auth loadUserAuthorities(@Param("userName") String username);
}
