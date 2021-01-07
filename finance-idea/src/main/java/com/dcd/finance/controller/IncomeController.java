package com.dcd.finance.controller;

import com.dcd.finance.annotation.UserLoginToken;
import com.dcd.finance.bean.Account;
import com.dcd.finance.bean.Income;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;
import com.dcd.finance.service.AccountService;
import com.dcd.finance.service.IncomeService;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/income")
public class IncomeController {
    @Resource
    IncomeService incomeService;


    @GetMapping("/selectIncome")
    public ResponseEntity<Object> selectIncome(String actName, String name, PageRequest pageQuery){
        PageResult AccountList = incomeService.selectIncome(actName,name,pageQuery);
        return new ResponseEntity<>(AccountList, HttpStatus.OK);
    }

    @GetMapping("/allAccount")
    public Object allAccount(){
        return incomeService.allAccount();
    }
    @GetMapping("/nameByActId")
    public Object nameByActId(int actId){ return  incomeService.nameByActId(actId); }
    @PostMapping("/addIncome")
    public ResponseEntity<Object> addIncome(@RequestBody Income income){
        String add = incomeService.addIncome(income);
        return new ResponseEntity<>(add,HttpStatus.OK);
    }
    @UserLoginToken
    @GetMapping("/deleteIncome")
    public ResponseEntity<Object> deleteIncome(int inId){
        String delete = incomeService.deleteIncome(inId);
        return new ResponseEntity<>(delete,HttpStatus.OK);
    }
    @PostMapping("/updateIncome")
    public ResponseEntity<Object> updateIncome(@RequestBody Income income){
        String update = incomeService.updateIncome(income);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }
    @PostMapping("/addCome")
    public ResponseEntity<Object> addCome(@RequestBody Object object){
        String ing = incomeService.addCome(object);
        return new ResponseEntity<>(ing,HttpStatus.OK);
    }
}
