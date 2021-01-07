package com.dcd.finance.mapper;

import com.dcd.finance.bean.Login;
import com.dcd.finance.bean.User;
import com.dcd.finance.common.PageRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoginMapper {
    List<Login> selectLogin(Login login);
    String findLoginById(String id);
    List<Login> loginUser(Login login);
}