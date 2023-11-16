package com.bu.deploy.service;

import com.bu.deploy.dto.GroupEntity;
import com.bu.deploy.dto.UserEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/11/14 10:18
 * @mark IdentifyService
 */
public interface IdentifyService {

    void addUser(UserEntity userEntity);

    List<UserEntity> list(Integer pageNum, Integer pageSize);

    void addGroup(GroupEntity groupEntity);

    void relationGroup(String userId, String groupId);

    Boolean removeUserRelation(String userId);

    PageInfo<GroupEntity> listGroup(Integer pageNum, Integer pageSize);

    Boolean deleteGroup(String groupId);

    List<GroupEntity> listGroup();
}
