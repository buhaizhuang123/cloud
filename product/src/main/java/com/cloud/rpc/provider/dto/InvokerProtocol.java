package com.cloud.rpc.provider.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author haizhuangbu
 * @date 2022/7/23 12:47
 * @mark InvokerProtocol
 */
@Data
public class InvokerProtocol  implements Serializable {

    private String className;

    private String methodName;

    private Class<?>[] parames;

    private Object[] values;


}
