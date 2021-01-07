package com.dcd.finance.mapper;

import com.dcd.finance.bean.Account;
import com.dcd.finance.bean.User;
import com.dcd.finance.common.PageRequest;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface AccountMapper {
    List<Object> selectAccount(String actName,String name, PageRequest pageQuery);
    List<Object> allUser();
    void addAccount(@Param("account") Account account);
    void deleteAccount(int actId);
    void updateAccount(Account account);
    List<Object> accountById(int actId);
    List<Object> accountByUser(int actId);
    //用户创建账户
    void accountCreate(Object object);
    List<Object> seeAccount(Object object);
    void inActId(Object object);
    //用户加入账户
    List<Object> accountCome(Object object);
    //用户修改账户信息
    void upAct(Object object);
}
