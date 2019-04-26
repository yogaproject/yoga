package com.woniu.yoga.crowdfunding.service.Impl;

import com.woniu.yoga.crowdfunding.dao.CrowdFundingMapper;
import com.woniu.yoga.crowdfunding.pojo.CrowdFunding;
import com.woniu.yoga.crowdfunding.pojo.Supporter;
import com.woniu.yoga.crowdfunding.service.CrowdFundingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrowdFundingServiceImpl implements CrowdFundingService {

    @Autowired
    private CrowdFundingMapper crowdFundingMapper;

    @Override
    public CrowdFunding selCfById(Integer cfId) {
        return crowdFundingMapper.selOneCf(cfId);
    }

    @Override
    public int updateStatus(CrowdFunding crowdFunding) {
        return crowdFundingMapper.updateByPrimaryKeySelective(crowdFunding);
    }

    @Override
    public int updateCf(CrowdFunding crowdFunding) {
        return crowdFundingMapper.updateByPrimaryKeySelective(crowdFunding);
    }

    @Override
    public ArrayList<CrowdFunding> getCfs(CrowdFunding crowdFunding,String orderCondition,String orderType) {
        return crowdFundingMapper.getCfs(crowdFunding,orderCondition, orderType);
    }

    @Override
    public int updateCurMoney(Supporter supporter) {
        return crowdFundingMapper.updateCurMoney(supporter);
    }

    @Override
    public int updateSupCount(Supporter supporter) {
        return crowdFundingMapper.updateSupCount(supporter);
    }

    @Override
    public List<CrowdFunding> getMyFoc(Integer userId) {
        return crowdFundingMapper.getMyFoc(userId);
    }

    @Override
    public int insertFoc(Integer userId, Integer cfId) {
        return crowdFundingMapper.insertFoc(userId,cfId);
    }

    @Override
    public int modifyFoc(Integer userId, Integer cfId) {
        return crowdFundingMapper.updateFoc(userId,cfId);
    }

    @Override
    public int insertCf(CrowdFunding crowdFunding) {
        return crowdFundingMapper.insertSelective(crowdFunding);
    }


}
