package com.dcd.finance.mapper;

import com.dcd.finance.bean.Income;
import com.dcd.finance.bean.Pay;
import com.dcd.finance.common.PageRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayMapper {
    List<Object> selectPay(String actName, String name, PageRequest pageQuery);
    void addPay(@Param("pay") Pay pay);
    void addUpdate(@Param("pay") Pay pay);
    void deletePay(int payId);
    void updatePay(@Param("pay") Pay pay);
    void userPay(Object object);
    void actPay(Object object);
}
