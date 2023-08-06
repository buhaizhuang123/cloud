package com.cloud.sys.service.impl;

import com.cloud.sys.dao.UserMapper;
import com.cloud.sys.dto.User;
import com.cloud.sys.service.FlowableService;
import com.cloud.sys.service.UserService;
import com.cloud.sys.to.IdentityUserInfo;
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

    @Autowired
    private FlowableService flowableService;

    @Override
    public void addUser(User user) {
        user.setUserId(UUID.randomUUID().toString().replace("-", ""));
        user.setUserAuth("ADMIN,SIMPLE");
        user.setUserEnable("Y");
        userMapper.addUser(user);
        IdentityUserInfo identityUserInfo = new IdentityUserInfo();
        identityUserInfo.setUserName(user.getUsername())
                .setPassword(user.getPassword())
                .setUserId(user.getUserId()).setGroupId(user.getGroup());
        // todo 通知工作流系统 创建用户
        flowableService.saveIdentityUser(identityUserInfo);

    }
}
