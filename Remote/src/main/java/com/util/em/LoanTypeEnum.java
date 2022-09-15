package com.util.em;

/**
 * @author haizhuangbu
 * @date 2022/9/14 17:36
 * @mark LoanTypeEnum
 */
public enum LoanTypeEnum {
    SETL("SETL");

    LoanTypeEnum(String code) {
        this.code = code;
    }

    private String code;

}
