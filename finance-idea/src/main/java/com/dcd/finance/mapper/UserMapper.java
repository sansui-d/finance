package com.dcd.finance.mapper;

import com.dcd.finance.bean.User;
import com.dcd.finance.common.PageRequest;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.List;

public interface UserMapper {
    //查询全部
    List<User> selectPage();
    //添加用户
    void insertUser(@Param("user")User user);
    //查询用户
    List<User> selectUser(String name, String loginId, PageRequest pageQuery);
    //删除用户
    void deleteUser(int userId);
    //修改用户
    void updateUser(User user);
    //查找用户所以信息
    List<Object> getUser(User user);
    List<Object> getAccount(User user);
    List<Object> getIncome(User user);
    List<Object> getPay(User user);
    void upUser(Object object);
    void register(Object object);
    //eCharts
    List<Object> allUser();
    List<Object> allAccount();
    List<Object> allPay();
    List<Object> allIncome();
}
