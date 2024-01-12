package com.cloud.sys.service;

import com.cloud.sys.dto.GroupEntity;
import com.cloud.sys.dto.User;
import com.cloud.sys.dto.UserEntity;
import com.github.pagehelper.PageInfo;

/**
 * @author haizhuangbu
 * @date 2023/3/24 22:10
 * @mark UserService
 */
public interface UserService {

    void addUser(User user);


    void addUserEntity(UserEntity entity);

    void addUserGroup(GroupEntity entity);

    PageInfo<User> listUser(Integer pageNum, Integer pageSize);

    Integer deleteUser(String userId);

}
