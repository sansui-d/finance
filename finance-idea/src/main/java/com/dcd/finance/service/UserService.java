package com.dcd.finance.service;

import com.dcd.finance.bean.User;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {
    PageResult findPage(PageRequest pageRequest);
    String addUser(User user);
    PageResult selectUser(String name,String loginId,PageRequest pageRequest);
    String deleteUser(int userId);
    String updateUser(User user);
    HashMap getUser(User user);
    String upUser(Object object);
    String register(Object object);
    HashMap allUser();
}
