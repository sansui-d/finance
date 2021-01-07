package com.dcd.finance.controller;

import com.dcd.finance.annotation.UserLoginToken;
import com.dcd.finance.bean.Insure;
import com.dcd.finance.bean.Loan;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;
import com.dcd.finance.service.IncomeService;
import com.dcd.finance.service.InsureService;
import com.dcd.finance.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {
    @Resource
    LoanService loanService;
    @GetMapping("/selectLoan")
    public ResponseEntity<Object> selectLoan(String loanName, PageRequest pageQuery){
        PageResult AccountList = loanService.selectLoan(loanName,pageQuery);
        return new ResponseEntity<>(AccountList, HttpStatus.OK);
    }

    @PostMapping("/addLoan")
    public ResponseEntity<Object> addLoan(@RequestBody Loan loan){
        String add = loanService.addLoan(loan);
        return new ResponseEntity<>(add,HttpStatus.OK);
    }
    @UserLoginToken
    @GetMapping("/deleteLoan")
    public ResponseEntity<Object> deleteLoan(int loanId){
        String delete = loanService.deleteLoan(loanId);
        return new ResponseEntity<>(delete,HttpStatus.OK);
    }
    @PostMapping("/updateLoan")
    public ResponseEntity<Object> updateLoan(@RequestBody Loan loan){
        String update = loanService.updateLoan(loan);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }
    @GetMapping("/allLoan")
    public ResponseEntity<Object> allLoan(){
        List<Object> obj = loanService.allLoan();
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }
}
