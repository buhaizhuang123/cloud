package com.cloud.sys.vo;

import com.cloud.common.Page;
import lombok.Data;

/**
 * @author haizhuangbu
 * @date 2023/8/5 22:18
 * @mark DeptVo
 */
@Data
public class DeptVo extends Page {

    private String deptName;

    private String deptCode;

}
