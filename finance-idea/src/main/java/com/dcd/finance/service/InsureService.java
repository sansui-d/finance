package com.dcd.finance.service;

import com.dcd.finance.bean.Income;
import com.dcd.finance.bean.Insure;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;

import java.util.List;

public interface InsureService {
    PageResult selectInsure(String sureTitle, PageRequest pageRequest);
    String addInsure(Insure insure);
    String deleteInsure(int sureId);
    String updateInsure(Insure insure);
    List<Object> allInsure();
}
