package com.dcd.finance.controller;

import com.dcd.finance.annotation.UserLoginToken;
import com.dcd.finance.bean.Account;
import com.dcd.finance.bean.Debt;
import com.dcd.finance.bean.User;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;
import com.dcd.finance.service.AccountService;
import com.dcd.finance.service.BudgetService;
import com.dcd.finance.service.DebtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/budget")
public class BudgetController {
    @Resource
    BudgetService budgetService;


    @GetMapping("/selectBudget")
    public ResponseEntity<Object> selectBudget(int actId,int userId){
        List<Object> AccountList = budgetService.selectBudget(actId,userId);
        return new ResponseEntity<>(AccountList, HttpStatus.OK);
    }
    @PostMapping("/addBudget")
    public ResponseEntity<Object> addBudget(@RequestBody Object object){
        String add = budgetService.addBudget(object);
        return new ResponseEntity<>(add,HttpStatus.OK);
    }
    @UserLoginToken
    @GetMapping("/deleteBudget")
    public ResponseEntity<Object> deleteBudget(int bId){
        String delete = budgetService.deleteBudget(bId);
        return new ResponseEntity<>(delete,HttpStatus.OK);
    }
    @GetMapping("/allBudget")
    public ResponseEntity<Object> allBudget(String type,PageRequest pageRequest){
        PageResult AccountList = budgetService.allBudget(type,pageRequest);
        return new ResponseEntity<>(AccountList, HttpStatus.OK);
    }
}
