package com.cloud.loanAfter.LoanRever.dao;

import com.cloud.loanAfter.LoanRever.dto.LoanRever;
import com.cloud.loanAfter.LoanRever.vo.Loan;
import com.cloud.shop.dto.Page;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/2/3 14:05
 * @mark LoanBackOut
 */
public interface LoanBackOutDao {

    List<LoanRever> list(Loan loan, RowBounds rowBounds);

}
