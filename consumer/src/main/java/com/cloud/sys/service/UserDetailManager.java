package com.cloud.sys.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author haizhuangbu
 * @date 2022/4/28 10:12
 * @mark UserDetailManager
 */
public interface UserDetailManager extends UserDetailsService {
    /**
     * 创建用户
     */
    void createUser(UserDetails userDetails);

    /**
     * 更新用户
     */
    void updateUser(UserDetails userDetails);

    /**
     * 删除用户
     */
    void deleteUser(String username);

    /**
     * 更改密码
     */
    void changePassword(String oldPass,String newPass);

    /**
     * 判断指定用户是否存在
     */
    Boolean userExists(String username);

}
