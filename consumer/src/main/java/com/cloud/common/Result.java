package com.cloud.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author haizhuangbu
 * @date 2022/6/6 11:04
 * @mark Result
 */
@Data
public class Result<T> {

    private Integer code;

    private String message;

    private Boolean success;

    private T data;


    public Result<T> success(T data) {
        this.code = HttpStatus.OK.value();

        this.message = HttpStatus.OK.getReasonPhrase();

        this.success = true;

        this.data = data;
        return this;
    }
}
