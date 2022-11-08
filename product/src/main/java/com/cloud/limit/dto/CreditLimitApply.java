package com.cloud.limit.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author haizhuangbu
 * @date 2022/11/4 17:48
 * @mark CreditLimit
 */
@Data
public class CreditLimitApply {

    private Integer limitNo;

    private String applSeq;

    private String custName;

    private String idNo;

    private String mobilePhone;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date applyDt;

    private String limitSts;

}
