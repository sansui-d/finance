package com.dcd.finance.service.Impl;

import com.dcd.finance.bean.Account;
import com.dcd.finance.bean.User;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;
import com.dcd.finance.common.PageUtils;
import com.dcd.finance.mapper.AccountMapper;
import com.dcd.finance.service.AccountService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class AccountImpl implements AccountService {
    @Resource
    AccountMapper AccountMapper;

    @Override
    public PageResult selectAccount(String actName, String name, PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<Object> account = AccountMapper.selectAccount(actName,name,pageRequest);
        PageInfo<Object> selectAccount = new PageInfo<Object>(account);
        return PageUtils.getPageResult(pageRequest,selectAccount);
    }
    @Override
    public List<Object> allUser(){
        List<Object> allUSer = AccountMapper.allUser();
        return AccountMapper.allUser();
    }

    public String addAccount(Account account){
        try {
            AccountMapper.addAccount(account);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public String deleteAccount(int actId){
        try {
            AccountMapper.deleteAccount(actId);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public String updateAccount(Account account){
        try {
            AccountMapper.updateAccount(account);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public HashMap accountByAll(int actId){
        List<Object> obj1 = AccountMapper.accountById(actId);
        List<Object> obj2 = AccountMapper.accountByUser(actId);
        HashMap<String,Object> map = new HashMap<>();
        map.put("account",obj1);
        map.put("user",obj2);
        return map;
    }
    public HashMap accountCreate(Object object){
        try {
            HashMap<String,Object> map = new HashMap<>();
            AccountMapper.accountCreate(object);
            map.put("is", "200");
            List<Object> obj = AccountMapper.seeAccount(object);
            map.put("account",obj);
            return map;
        }catch (Exception e){
            return null;
        }
    }
    public String inActId(Object object){
        try {
            AccountMapper.inActId(object);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public HashMap accountCome(Object object){
        try {
            HashMap<String,Object> map = new HashMap<>();
            List<Object> obj = AccountMapper.accountCome(object);
            map.put("account",obj);
            map.put("is","200");
            return map;
        }catch (Exception e){
            return null;
        }
    }
    public String upAct(Object object){
        try {
            AccountMapper.upAct(object);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
}
