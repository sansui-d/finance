package com.dcd.finance.service;

import com.dcd.finance.bean.Account;
import com.dcd.finance.bean.Debt;
import com.dcd.finance.bean.User;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;

import java.util.HashMap;
import java.util.List;

public interface BudgetService {
    List<Object> selectBudget(int actId, int userId);
    String addBudget(Object object);
    String deleteBudget(int bId);
    PageResult allBudget(String type,PageRequest pageRequest);
}
