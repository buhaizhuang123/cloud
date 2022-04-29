package com.cloud.sys.service.impl;

import com.cloud.sys.service.UserDetailManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author haizhuangbu
 * @date 2022/4/28 10:24
 * @mark UserDetailManagerImpl
 */
public class UserDetailManagerImpl implements UserDetailManager {
    @Override
    public void createUser(UserDetails userDetails) {

    }

    @Override
    public void updateUser(UserDetails userDetails) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPass, String newPass) {

    }

    @Override
    public Boolean userExists(String username) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
