package com.dcd.finance.controller;

import com.dcd.finance.annotation.UserLoginToken;
import com.dcd.finance.bean.User;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;
import com.dcd.finance.service.UserService;

import javafx.geometry.HPos;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.lang.model.element.NestingKind;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @UserLoginToken
    @PostMapping("/findPage")
    public Object findPage(@RequestBody PageRequest pageQuery) {
        return userService.findPage(pageQuery);
    }
    @UserLoginToken
    @PostMapping("/addUser")
    public ResponseEntity<Object> addUser(@RequestBody User user){
        String addUser = userService.addUser(user);
        return new ResponseEntity<>(addUser,HttpStatus.OK);
    }
    @UserLoginToken
    @GetMapping("/selectUser")
    public ResponseEntity<Object> selectUser(String name,String loginId,PageRequest pageQuery){
        PageResult userList = userService.selectUser(name,loginId,pageQuery);
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }
    @UserLoginToken
    @GetMapping("/deleteUser")
    public ResponseEntity<Object> deleteUser(int userId){
        String delete = userService.deleteUser(userId);
        return new ResponseEntity<>(delete,HttpStatus.OK);
    }
    @UserLoginToken
    @PostMapping("/updateUser")
    public ResponseEntity<Object> updateUser(@RequestBody User user){
        String update = userService.updateUser(user);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }
    @UserLoginToken
    @PostMapping("/getUser")
    public ResponseEntity<Object> getUser(@RequestBody User user){
        HashMap obj = userService.getUser(user);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }
    @PostMapping("/upUser")
    public ResponseEntity<Object> upUser(@RequestBody Object object){
        String obj = userService.upUser(object);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody Object object){
        String obj = userService.register(object);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }
    //Echarts
    @GetMapping("/eCharts")
    public ResponseEntity<Object> allUser(){
        HashMap obj = userService.allUser();
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }
//    @GetMapping
//    @PostMapping
//    @PutMapping
//    @DeleteMapping
}
