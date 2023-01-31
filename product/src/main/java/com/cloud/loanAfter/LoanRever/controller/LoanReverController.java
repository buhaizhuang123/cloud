package com.cloud.loanAfter.LoanRever.controller;

import com.cloud.loanAfter.LoanRever.dto.LoanRever;
import com.cloud.loanAfter.LoanRever.vo.Loan;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/1/31 09:11
 * @mark LoanReverController
 */
@RestController
@RequestMapping("loanRever")
public class LoanReverController {



    @RequestMapping(value = "list",method = RequestMethod.POST)
    public List<LoanRever> list(@RequestBody Loan loan, @RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum){
        ArrayList<LoanRever> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            LoanRever loanRever = new LoanRever();
            loanRever.setCustName(loan.getCustName());
            loanRever.setIdNo(loan.getIdNo());
            loanRever.setLoanDate(new Date());
            loanRever.setContNo(i+ 1 + "");
            loanRever.setLoanTerm(i);
            loanRever.setIsRever("1");
            list.add(loanRever);
        }
        return list;
    }



}
