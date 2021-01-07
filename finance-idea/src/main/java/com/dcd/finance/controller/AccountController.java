package com.dcd.finance.controller;

import com.dcd.finance.annotation.UserLoginToken;
import com.dcd.finance.bean.Account;
import com.dcd.finance.bean.User;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;
import com.dcd.finance.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Resource
    AccountService accountService;


    @GetMapping("/selectAccount")
    public ResponseEntity<Object> selectAccount(String actName, String name, PageRequest pageQuery){
        PageResult AccountList = accountService.selectAccount(actName,name,pageQuery);
        return new ResponseEntity<>(AccountList, HttpStatus.OK);
    }
    @GetMapping("/allUser")
    public Object allUser(){
        return accountService.allUser();
    }
    @PostMapping("/addAccount")
    public ResponseEntity<Object> addAccount(@RequestBody Account account){
        String add = accountService.addAccount(account);
        return new ResponseEntity<>(add,HttpStatus.OK);
    }
    @UserLoginToken
    @GetMapping("/deleteAccount")
    public ResponseEntity<Object> deleteAccount(int actId){
        String delete = accountService.deleteAccount(actId);
        return new ResponseEntity<>(delete,HttpStatus.OK);
    }
    @PostMapping("/updateAccount")
    public ResponseEntity<Object> updateAccount(@RequestBody Account account){
        String update = accountService.updateAccount(account);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }
    @GetMapping("/accountByAll")
    public ResponseEntity<Object> accountByAll(int actId){
        HashMap ha = accountService.accountByAll(actId);
        return new ResponseEntity<>(ha,HttpStatus.OK);
    }
    @PostMapping("/accountCreate")
    public ResponseEntity<Object> accountCreate(@RequestBody Object object){
        HashMap a = accountService.accountCreate(object);
        return new ResponseEntity<>(a,HttpStatus.OK);
    }
    @PostMapping("/inActId")
    public ResponseEntity<Object> inActId(@RequestBody Object object){
        String c = accountService.inActId(object);
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
    @PostMapping("/accountCome")
    public ResponseEntity<Object> accountCome(@RequestBody Object object){
        HashMap b = accountService.accountCome(object);
        return new ResponseEntity<>(b,HttpStatus.OK);
    }
    @PostMapping("/upAct")
    public ResponseEntity<Object> upAct(@RequestBody Object object){
        String a = accountService.upAct(object);
        return new ResponseEntity<>(a,HttpStatus.OK);
    }
}
