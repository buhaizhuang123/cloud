package com.cloud.common;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

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

    public static void main(String[] args) {


        String json = "[1,2,3";

        try {
            List<Integer> integers = JSONObject.parseArray(json, Integer.class);
        } catch (Exception e) {
            System.out.println("format error");
            System.out.println(e.getMessage());
        }
        System.out.println(json);

    }

}
