package com.cloud.sys.to;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author haizhuangbu
 * @date 2023/8/6 10:49
 * @mark IdentityUserInfo
 */
@Data
@Accessors(chain = true)
public class IdentityUserInfo {



    private String userId;

    private String userName;

    private String groupId;

    private String password;

}
