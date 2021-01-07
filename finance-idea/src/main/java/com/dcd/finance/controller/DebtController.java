package com.dcd.finance.controller;

import com.dcd.finance.annotation.UserLoginToken;
import com.dcd.finance.bean.Account;
import com.dcd.finance.bean.Debt;
import com.dcd.finance.bean.User;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;
import com.dcd.finance.service.AccountService;
import com.dcd.finance.service.DebtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/debt")
public class DebtController {
    @Resource
    DebtService debtService;


    @GetMapping("/selectDebt")
    public ResponseEntity<Object> selectDebt(String type, String debtName, PageRequest pageQuery){
        PageResult AccountList = debtService.selectDebt(type,debtName,pageQuery);
        return new ResponseEntity<>(AccountList, HttpStatus.OK);
    }
    @PostMapping("/addDebt")
    public ResponseEntity<Object> addDebt(@RequestBody Object object){
        String add = debtService.addDebt(object);
        return new ResponseEntity<>(add,HttpStatus.OK);
    }
    @UserLoginToken
    @PostMapping("/deleteDebt")
    public ResponseEntity<Object> deleteDebt(@RequestBody Object object){
        String delete = debtService.deleteDebt(object);
        return new ResponseEntity<>(delete,HttpStatus.OK);
    }
    @PostMapping("/updateDebt")
    public ResponseEntity<Object> updateDebt(@RequestBody Debt debt){
        String update = debtService.updateDebt(debt);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }
    @GetMapping("/userDebt")
    public ResponseEntity<Object> userDebt(String userId,String actId){
        List<Object> obj = debtService.userDebt(userId,actId);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }
}
