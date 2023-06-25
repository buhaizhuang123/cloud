package com.bu.common.to;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author haizhuangbu
 * @date 2023/6/19 16:56
 * @mark WaitExeTaskTo
 */
@Data
public class WaitExeTaskTo {

    private String id;

    private String name;

    private String assignee;

    private Boolean isSuspend;

    private int priorty;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date claimTime;

    public static void main(String[] args) {

    }





}
