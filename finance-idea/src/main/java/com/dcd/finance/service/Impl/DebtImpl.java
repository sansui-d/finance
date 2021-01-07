package com.dcd.finance.service.Impl;

import com.dcd.finance.bean.Debt;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;
import com.dcd.finance.common.PageUtils;
import com.dcd.finance.service.DebtService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DebtImpl implements DebtService {
    @Resource
    com.dcd.finance.mapper.DebtMapper DebtMapper;

    @Override
    public PageResult selectDebt(String type, String debtName, PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<Object> account = DebtMapper.selectDebt(type,debtName,pageRequest);
        PageInfo<Object> selectAccount = new PageInfo<Object>(account);
        return PageUtils.getPageResult(pageRequest,selectAccount);
    }

    public String addDebt(Object object){
        try {
            DebtMapper.addDebt(object);
            DebtMapper.addSurplus((object));
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public String deleteDebt(Object object){
        try {
            DebtMapper.deleteDebt(object);
            DebtMapper.addSurplus(object);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public String updateDebt(Debt debt){
        try {
            DebtMapper.updateDebt(debt);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public List<Object> userDebt(String userId, String actId){
            return DebtMapper.userDebt(userId,actId);
    }
}
