package com.dcd.finance.mapper;

import com.dcd.finance.bean.Account;
import com.dcd.finance.bean.Income;
import com.dcd.finance.common.PageRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IncomeMapper {
    List<Object> selectIncome(String actName, String name, PageRequest pageQuery);
    List<Object> allAccount();
    List<Object> nameByActId(int actId);
    void addIncome(@Param("income") Income income);
    void addUpdate(@Param("income") Income income);
    void deleteIncome(int inId);
    void updateIncome(@Param("income") Income income);
    void addCome(Object object);
    void upCome(Object object);
}
