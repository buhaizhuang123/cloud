package com.cloud.usr.dto;


import lombok.Data;

/**
 * @author haizhuangbu
 * @date 2022/4/29 09:45
 * @mark Usr
 */
@Data
public class Usr {
    /**
     * @field 用户id
     */
    private String userId;

    /**
     * @field 用户名
     */
    private String userName;

    /**
     * @field 用户密码
     */
    private String userPass;

    /**
     * @field 用户类型
     */
    private Character userEnable;


}
