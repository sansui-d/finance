package com.dcd.finance.controller;


import com.dcd.finance.annotation.UserLoginToken;
import com.dcd.finance.bean.Login;
import com.dcd.finance.common.TokenUtil;
import com.dcd.finance.service.LoginService;
import com.dcd.finance.service.TokenService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    LoginService loginService;
    @Resource
    TokenService tokenService;

    /**
     * 登录方法

     * @return ModelAndView
     */
    @PostMapping("/loginAdmin")
    public ResponseEntity<Object> selectLogin(@RequestBody Login login){
        List<Login> loginA = loginService.selectLogin(login);
        String token = tokenService.getToken(login);
        Map<String,String> map = new HashMap<String,String>();
        map.put("token",token);
        for (Login name : loginA) {
            String admin = name.getName();
            map.put("admin", admin);
        }
        String isLogin = "";
        if(loginA.size()==0){
            isLogin = "400";
        }
        else {
            isLogin = "200";
        }
        map.put("isLogin",isLogin);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @PostMapping("/loginUser")
    public ResponseEntity<Object> loginUser(@RequestBody Login login){
        List<Login> loginB = loginService.loginUser(login);
        String tokenA = tokenService.getToken(login);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("token",tokenA);
        String isLogin;
        if(loginB.size()==0){
            isLogin = "400";
        }
        else {
            isLogin = "200";
        }
        map.put("isLogin",isLogin);
        map.put("user",loginB);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
}
