package com.dcd.finance.mapper;

import com.dcd.finance.bean.Account;
import com.dcd.finance.bean.Income;
import com.dcd.finance.bean.Insure;
import com.dcd.finance.bean.Loan;
import com.dcd.finance.common.PageRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoanMapper {
    List<Object> selectLoan(String loanName, PageRequest pageQuery);
    void addLoan(@Param("loan") Loan loan);
    void deleteLoan(int loanId);
    void updateLoan(@Param("loan") Loan loan);
    //app
    List<Object> allLoan();
}
