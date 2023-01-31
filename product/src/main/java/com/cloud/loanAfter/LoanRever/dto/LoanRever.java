package com.cloud.loanAfter.LoanRever.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author haizhuangbu
 * @date 2023/1/31 11:24
 * @mark LoanRever
 */
@Data
public class LoanRever {

    private String contNo;

    private String custName;

    private String idNo;

    private String loanType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date loanDate;

    private Integer loanTerm;

    private String loanSts;

    private String isRever;

}
