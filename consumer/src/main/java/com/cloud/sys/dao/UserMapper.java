package com.cloud.sys.dao;

import com.cloud.sys.dto.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/6/1 20:02
 * @mark UserMapper
 */
public interface UserMapper {

    List<User> findUser(RowBounds rowBounds);

    User loadUserByUsername(@Param("userName") String userName);

    void addUser(User user);

    List<User> listUser();

    Integer deleteUser(String userId);
}
