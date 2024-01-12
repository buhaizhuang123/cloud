package com.cloud.sys.dto;

import lombok.Data;

/**
 * @author haizhuangbu
 * @date 2023/11/14 10:26
 * @mark UserEntity
 */
@Data
public class UserEntity {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String tenantId;

    private String groupId;

}
