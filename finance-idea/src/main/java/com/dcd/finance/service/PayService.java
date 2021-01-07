package com.dcd.finance.service;

import com.dcd.finance.bean.Income;
import com.dcd.finance.bean.Pay;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;

public interface PayService {
    PageResult selectPay(String actName, String name, PageRequest pageRequest);
    String addPay(Pay pay);
    String deletePay(int payId);
    String updatePay(Pay pay);
    String userPay(Object object);
}
