package com.dcd.finance.service.Impl;

import com.dcd.finance.bean.Income;
import com.dcd.finance.bean.Pay;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;
import com.dcd.finance.common.PageUtils;
import com.dcd.finance.mapper.IncomeMapper;
import com.dcd.finance.mapper.PayMapper;
import com.dcd.finance.service.PayService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PayImpl implements PayService {
    @Resource
    PayMapper PayMapper;

    @Override
    public PageResult selectPay(String actName, String name, PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<Object> pay = PayMapper.selectPay(actName,name,pageRequest);
        PageInfo<Object> selectPay = new PageInfo<Object>(pay);
        return PageUtils.getPageResult(pageRequest,selectPay);
    }

    @Override
    @Transactional
    public String addPay(Pay pay){
        try {
            PayMapper.addPay(pay);
            PayMapper.addUpdate(pay);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public String deletePay(int payId){
        try {
            PayMapper.deletePay(payId);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public String updatePay(Pay pay){
        try {
            PayMapper.updatePay(pay);
            PayMapper.addUpdate(pay);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public String userPay(Object object){
        try {
            PayMapper.userPay(object);
            PayMapper.actPay(object);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
}
