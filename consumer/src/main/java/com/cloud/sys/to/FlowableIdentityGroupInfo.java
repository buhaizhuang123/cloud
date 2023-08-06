package com.cloud.sys.to;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author haizhuangbu
 * @date 2023/8/5 21:26
 * @mark FlowableIdentityGroupInfo
 */
@Data
@Accessors(chain = true)
public class FlowableIdentityGroupInfo {

    private String groupId;

    private String groupName;

    private String groupType;

}
