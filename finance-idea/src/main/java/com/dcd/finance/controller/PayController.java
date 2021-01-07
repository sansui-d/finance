package com.dcd.finance.controller;

import com.dcd.finance.annotation.UserLoginToken;
import com.dcd.finance.bean.Income;
import com.dcd.finance.bean.Pay;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;
import com.dcd.finance.service.AccountService;
import com.dcd.finance.service.IncomeService;
import com.dcd.finance.service.PayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/pay")
public class PayController {
    @Resource
    PayService payService;


    @GetMapping("/selectPay")
    public ResponseEntity<Object> selectPay(String actName, String name, PageRequest pageQuery){
        PageResult AccountList = payService.selectPay(actName,name,pageQuery);
        return new ResponseEntity<>(AccountList, HttpStatus.OK);
    }

    @PostMapping("/addPay")
    public ResponseEntity<Object> addIncome(@RequestBody Pay pay){
        String add = payService.addPay(pay);
        return new ResponseEntity<>(add,HttpStatus.OK);
    }
    @UserLoginToken
    @GetMapping("/deletePay")
    public ResponseEntity<Object> deletePay(int payId){
        String delete = payService.deletePay(payId);
        return new ResponseEntity<>(delete,HttpStatus.OK);
    }
    @PostMapping("/updatePay")
    public ResponseEntity<Object> updatePay(@RequestBody Pay pay){
        String update = payService.updatePay(pay);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }
    @PostMapping("/userPay")
    public ResponseEntity<Object> userPay(@RequestBody Object object){
        String obj = payService.userPay(object);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }
}
