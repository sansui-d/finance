package com.dcd.finance.service.Impl;

import com.dcd.finance.bean.Income;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;
import com.dcd.finance.common.PageUtils;
import com.dcd.finance.mapper.IncomeMapper;
import com.dcd.finance.service.IncomeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IncomeImpl implements IncomeService {
    @Resource
    IncomeMapper IncomeMapper;

    @Override
    public PageResult selectIncome(String actName, String name, PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<Object> income = IncomeMapper.selectIncome(actName,name,pageRequest);
        PageInfo<Object> selectIncome = new PageInfo<Object>(income);
        return PageUtils.getPageResult(pageRequest,selectIncome);
    }
    @Override
    public List<Object> allAccount(){
        List<Object> allAccount = IncomeMapper.allAccount();
        return IncomeMapper.allAccount();
    }
    @Override
    public List<Object> nameByActId(int actId){
        List<Object> act = IncomeMapper.nameByActId(actId);
        return IncomeMapper.nameByActId(actId);
    }
    @Override
    @Transactional
    public String addIncome(Income income){
        try {
            IncomeMapper.addIncome(income);
            IncomeMapper.addUpdate(income);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public String deleteIncome(int inId){
        try {
            IncomeMapper.deleteIncome(inId);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public String updateIncome(Income income){
        try {
            IncomeMapper.updateIncome(income);
            IncomeMapper.addUpdate(income);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public String addCome(Object object){
        try {
            IncomeMapper.addCome(object);
            IncomeMapper.upCome(object);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
}
