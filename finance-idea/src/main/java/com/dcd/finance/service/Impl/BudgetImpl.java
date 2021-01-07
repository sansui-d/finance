package com.dcd.finance.service.Impl;

import com.dcd.finance.bean.Debt;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;
import com.dcd.finance.common.PageUtils;
import com.dcd.finance.service.BudgetService;
import com.dcd.finance.service.DebtService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BudgetImpl implements BudgetService {
    @Resource
    com.dcd.finance.mapper.BudgetMapper BudgetMapper;

    @Override
    public PageResult allBudget(String type,PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<Object> budget = BudgetMapper.allBudget(type,pageRequest);
        PageInfo<Object> all = new PageInfo<Object>(budget);
        return PageUtils.getPageResult(pageRequest,all);
    }
    public List<Object> selectBudget(int actId, int userId) {

        return BudgetMapper.selectBudget(actId,userId);
    }

    public String addBudget(Object object){
        try {
            BudgetMapper.addBudget(object);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public String deleteBudget(int bId) {
        try {
            BudgetMapper.deleteBudget(bId);
            return "200";
        } catch (Exception e) {
            return "400";
        }
    }

}
