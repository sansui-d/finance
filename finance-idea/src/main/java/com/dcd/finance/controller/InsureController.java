package com.dcd.finance.controller;

import com.dcd.finance.annotation.UserLoginToken;
import com.dcd.finance.bean.Insure;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;
import com.dcd.finance.service.IncomeService;
import com.dcd.finance.service.InsureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jws.Oneway;
import java.util.List;

@RestController
@RequestMapping("/insure")
public class InsureController {
    @Resource
    InsureService insureService;
    @GetMapping("/selectInsure")
    public ResponseEntity<Object> selectInsure(String sureTitle, PageRequest pageQuery){
        PageResult AccountList = insureService.selectInsure(sureTitle,pageQuery);
        return new ResponseEntity<>(AccountList, HttpStatus.OK);
    }

    @PostMapping("/addInsure")
    public ResponseEntity<Object> addInsure(@RequestBody Insure insure){
        String add = insureService.addInsure(insure);
        return new ResponseEntity<>(add,HttpStatus.OK);
    }
    @UserLoginToken
    @GetMapping("/deleteInsure")
    public ResponseEntity<Object> deleteInsure(int sureId){
        String delete = insureService.deleteInsure(sureId);
        return new ResponseEntity<>(delete,HttpStatus.OK);
    }
    @PostMapping("/updateInsure")
    public ResponseEntity<Object> updateInsure(@RequestBody Insure insure){
        String update = insureService.updateInsure(insure);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }
    @GetMapping("/allInsure")
    public ResponseEntity<Object> allInsure(){
        List<Object> obj = insureService.allInsure();
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }
}
