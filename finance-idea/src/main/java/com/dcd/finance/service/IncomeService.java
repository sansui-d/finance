package com.dcd.finance.service;

import com.dcd.finance.bean.Account;
import com.dcd.finance.bean.Income;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;

import java.util.List;

public interface IncomeService {
    PageResult selectIncome(String actName, String name, PageRequest pageRequest);
    List<Object> allAccount();
    List<Object> nameByActId(int actId);
    String addIncome(Income income);
    String deleteIncome(int inId);
    String updateIncome(Income income);
    String addCome(Object object);
}
