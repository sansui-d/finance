package com.dcd.finance.service.Impl;

import com.dcd.finance.bean.Login;
import com.dcd.finance.mapper.LoginMapper;
import com.dcd.finance.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoginImpl implements LoginService {
    @Resource
    LoginMapper loginMapper;
    public List<Login> selectLogin(Login login){
        List<Login> loginA =  loginMapper.selectLogin(login);
        return loginA;
    }
    public String findLoginById(String id){
        try {
            loginMapper.findLoginById(id);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public List<Login> loginUser(Login login){
        List<Login> loginB = loginMapper.loginUser(login);
        return loginB;
    }
}
