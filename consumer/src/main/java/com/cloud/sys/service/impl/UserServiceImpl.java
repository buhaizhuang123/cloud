package com.cloud.sys.service.impl;

import com.cloud.sys.dao.UserMapper;
import com.cloud.sys.dto.User;
import com.cloud.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author haizhuangbu
 * @date 2023/3/24 22:11
 * @mark UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        user.setUserId(UUID.randomUUID().toString().replace("-", ""));
        user.setUserAuth("ADMIN,SIMPLE");
        user.setUserEnable("Y");
        userMapper.addUser(user);
    }
}
