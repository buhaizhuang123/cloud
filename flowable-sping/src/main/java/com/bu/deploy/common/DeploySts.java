package com.bu.deploy.common;

/**
 * @author haizhuangbu
 * @date 2023/6/20 13:27
 * @mark DeploySts
 */
public enum DeploySts {
    INIT("init","未启用"),
    SUSPEND("suspend","暂停"),
    RUNNING("running","启用中"),
    DELETED("deleted","已删除")
    ;
    private String code;

    private String msg;

    DeploySts(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
