package com.dcd.finance.service.Impl;

import com.dcd.finance.bean.Income;
import com.dcd.finance.bean.Loan;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;
import com.dcd.finance.common.PageUtils;
import com.dcd.finance.mapper.LoanMapper;
import com.dcd.finance.service.IncomeService;
import com.dcd.finance.service.InsureService;
import com.dcd.finance.service.LoanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoanImpl implements LoanService {
    @Resource
    LoanMapper LoanMapper;

    @Override
    public PageResult selectLoan(String loanName, PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<Object> insure = LoanMapper.selectLoan(loanName,pageRequest);
        PageInfo<Object> selectInsure = new PageInfo<Object>(insure);
        return PageUtils.getPageResult(pageRequest,selectInsure);
    }
    @Override
    @Transactional
    public String addLoan(Loan loan){
        try {
            LoanMapper.addLoan(loan);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public String deleteLoan(int loanId){
        try {
            LoanMapper.deleteLoan(loanId);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public String updateLoan(Loan loan){
        try {
            LoanMapper.updateLoan(loan);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public List<Object> allLoan(){
        List<Object> obj = LoanMapper.allLoan();
        return obj;
    }
}
