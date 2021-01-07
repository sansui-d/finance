package com.dcd.finance.service;

import com.dcd.finance.bean.Login;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;

import java.util.List;

public interface LoginService {
    List<Login> selectLogin(Login login);
    String findLoginById(String id);
    List<Login> loginUser(Login login);
}