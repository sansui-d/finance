package com.dcd.finance.service;

import com.dcd.finance.bean.Income;
import com.dcd.finance.bean.Insure;
import com.dcd.finance.bean.Loan;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;

import java.util.List;

public interface LoanService {
    PageResult selectLoan(String loanName, PageRequest pageRequest);
    String addLoan(Loan loan);
    String deleteLoan(int loanId);
    String updateLoan(Loan loan);
    List<Object> allLoan();
}
