package com.dcd.finance.service.Impl;

import com.dcd.finance.bean.Income;
import com.dcd.finance.bean.Insure;
import com.dcd.finance.common.PageRequest;
import com.dcd.finance.common.PageResult;
import com.dcd.finance.common.PageUtils;
import com.dcd.finance.mapper.InsureMapper;
import com.dcd.finance.service.IncomeService;
import com.dcd.finance.service.InsureService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InsureImpl implements InsureService {
    @Resource
    InsureMapper InsureMapper;

    @Override
    public PageResult selectInsure(String sureTitle, PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<Object> insure = InsureMapper.selectInsure(sureTitle,pageRequest);
        PageInfo<Object> selectInsure = new PageInfo<Object>(insure);
        return PageUtils.getPageResult(pageRequest,selectInsure);
    }
    @Override
    @Transactional
    public String addInsure(Insure insure){
        try {
            InsureMapper.addInsure(insure);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public String deleteInsure(int sureId){
        try {
            InsureMapper.deleteInsure(sureId);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public String updateInsure(Insure insure){
        try {
            InsureMapper.updateInsure(insure);
            return "200";
        }catch (Exception e){
            return "400";
        }
    }
    public List<Object> allInsure(){
        List<Object> obj = InsureMapper.allInsure();
        return obj;
    }
}
