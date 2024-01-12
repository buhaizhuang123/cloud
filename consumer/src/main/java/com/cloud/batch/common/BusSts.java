package com.cloud.batch.common;

public enum BusSts {

    OK("1", "生效"),
    UN("2", "失效");


    private String code;

    private String msg;

    BusSts(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
