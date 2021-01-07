package com.dcd.finance.service.Impl;

import com.dcd.finance.bean.User;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;
import com.dcd.finance.common.PageUtils;
import com.dcd.finance.mapper.UserMapper;
import com.dcd.finance.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserImpl implements UserService {
    @Resource
    UserMapper userMapper;

    private PageInfo<User> getPageInfo(PageRequest pageRequest){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<User> allUser = userMapper.selectPage();
        return new PageInfo<User>(allUser);
    }
    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest,getPageInfo(pageRequest));
    }


    public String addUser(User user){
        try{
            userMapper.insertUser(user);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }

    @Override
    public PageResult selectUser(String name, String loginId,PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<User> user = userMapper.selectUser(name,loginId,pageRequest);
        PageInfo<User> selectUser = new PageInfo<User>(user);
        return PageUtils.getPageResult(pageRequest,selectUser);
    }

    //删除用户
    public String deleteUser(int userId){
        try {
            userMapper.deleteUser(userId);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    //修改用户
    public String updateUser(User user){
        try {
            userMapper.updateUser(user);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    //查找用户所有信息
    public HashMap getUser(User user){
        try {
            List<Object> obj1 = userMapper.getUser(user);
            List<Object> obj2 = userMapper.getIncome(user);
            List<Object> obj3 = userMapper.getPay(user);
            List<Object> obj4 = userMapper.getAccount(user);
            HashMap<String,Object> map = new HashMap<>();
            map.put("user",obj1);
            map.put("income",obj2);
            map.put("pay",obj3);
            map.put("account",obj4);
            return map;
        }catch (Exception e){
            return null;
        }
    }
    //用户自己修改个人信息
    public String upUser(Object object){
        try {
             userMapper.upUser(object);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public String register(Object object){
        try {
            userMapper.register(object);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public HashMap allUser(){
        try {
            List<Object> obj1 = userMapper.allAccount();
            List<Object> obj2 = userMapper.allUser();
            List<Object> obj3 = userMapper.allIncome();
            List<Object> obj4 = userMapper.allPay();
            HashMap<String,Object> map1 = new HashMap<>();
            map1.put("account",obj1);
            map1.put("user",obj2);
            map1.put("income",obj3);
            map1.put("pay",obj4);
            return map1;
        }catch (Exception e){
            return null;
        }
    }
}
