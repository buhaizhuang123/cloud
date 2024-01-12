package com.cloud.sys.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author haizhuangbu
 * @date 2023/8/5 21:02
 * @mark SDeptDo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SDeptDo {

    private String deptId;

    private String deptName;

    private String deptCode;

    private String deptType;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;

    private String isRunning;

}
