package com.dcd.finance.mapper;

import com.dcd.finance.bean.Account;
import com.dcd.finance.bean.Debt;
import com.dcd.finance.bean.User;
import com.dcd.finance.common.PageRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DebtMapper {
    List<Object> selectDebt(String type,String debtName, PageRequest pageQuery);
    void addDebt(Object object);
    void addSurplus(Object object);
    void deleteDebt(Object object);
    void updateDebt(Debt debt);
    List<Object> userDebt(String userId,String actId);
}
