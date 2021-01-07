package com.dcd.finance.service;

import com.dcd.finance.bean.Account;
import com.dcd.finance.bean.User;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;

import java.util.HashMap;
import java.util.List;

public interface AccountService {
    PageResult selectAccount(String actName, String name, PageRequest pageRequest);
    List<Object> allUser();
    String addAccount(Account account);
    String deleteAccount(int actId);
    String updateAccount(Account account);
    HashMap accountByAll(int actId);
    HashMap accountCreate(Object object);
    String inActId(Object object);
    HashMap accountCome(Object object);
    String upAct(Object object);
}
