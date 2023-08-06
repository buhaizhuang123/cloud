package com.bu.common.form;

import lombok.Data;

/**
 * @author haizhuangbu
 * @date 2023/8/6 08:53
 * @mark IdentityAddUserReq
 */
@Data
public class IdentityAddUserReq {

    private String userId;

    private String userName;

    private String groupId;

    private String password;

}
