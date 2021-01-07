package com.dcd.finance.mapper;

import com.dcd.finance.bean.Account;
import com.dcd.finance.bean.Income;
import com.dcd.finance.bean.Insure;
import com.dcd.finance.common.PageRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InsureMapper {
    List<Object> selectInsure(String sureTitle, PageRequest pageQuery);
    void addInsure(@Param("insure") Insure insure);
    void deleteInsure(int sureId);
    void updateInsure(@Param("insure") Insure insure);
    List<Object> allInsure();
}
