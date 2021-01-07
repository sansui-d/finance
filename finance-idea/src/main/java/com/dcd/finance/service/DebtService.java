package com.dcd.finance.service;

import com.dcd.finance.bean.Account;
import com.dcd.finance.bean.Debt;
import com.dcd.finance.bean.User;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;

import java.util.List;

public interface DebtService {
    PageResult selectDebt(String type, String debtName, PageRequest pageRequest);
    String addDebt(Object object);
    String deleteDebt(Object object);
    String updateDebt(Debt debt);
    List<Object> userDebt(String userId,String actId);
}
