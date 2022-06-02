package com.cloud.sys.dao;

import com.cloud.sys.dto.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/6/1 20:02
 * @mark UserMapper
 */
public interface UserMapper {

    List<User> findUser();

    User loadUserByUsername(@Param("userName") String userName);
}
