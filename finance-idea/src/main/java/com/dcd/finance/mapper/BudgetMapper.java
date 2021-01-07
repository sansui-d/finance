package com.dcd.finance.mapper;

import com.dcd.finance.bean.Account;
import com.dcd.finance.bean.Debt;
import com.dcd.finance.bean.User;
import com.dcd.finance.common.PageRequest;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface BudgetMapper {
    List<Object> selectBudget(int actId,int userId);
    void addBudget(Object object);
    void deleteBudget(int bId);
    List<Object> allBudget(String type,PageRequest pageRequest);
}
