package com.cloud.loanAfter.LoanRever.controller;

import com.cloud.loanAfter.LoanRever.dao.LoanBackOutDao;
import com.cloud.loanAfter.LoanRever.dto.LoanRever;
import com.cloud.loanAfter.LoanRever.vo.Loan;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private LoanBackOutDao loanBackOutDao;


    @RequestMapping(value = "list",method = RequestMethod.POST)
    public List<LoanRever> list(@RequestBody Loan loan, @RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum){
        RowBounds rowBounds = new RowBounds(pageNum, pageSize);
        return loanBackOutDao.list(loan, rowBounds);
    }



}
