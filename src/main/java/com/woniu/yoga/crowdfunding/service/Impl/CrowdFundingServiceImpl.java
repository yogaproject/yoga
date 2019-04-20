package com.woniu.yoga.crowdfunding.service.Impl;

import com.woniu.yoga.crowdfunding.dao.CrowdFundingMapper;
import com.woniu.yoga.crowdfunding.pojo.CrowdFunding;
import com.woniu.yoga.crowdfunding.service.CrowdFundingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CrowdFundingServiceImpl implements CrowdFundingService {

    @Autowired
    private CrowdFundingMapper crowdFundingMapper;

    @Override
    public CrowdFunding selCfById(Integer cfId) {
        return crowdFundingMapper.selectByPrimaryKey(cfId);
    }

    @Override
    public void updateStatus(CrowdFunding crowdFunding) {
        crowdFundingMapper.updateByPrimaryKeySelective(crowdFunding);
    }

    @Override
    public void updateCf(CrowdFunding crowdFunding) {
        crowdFundingMapper.updateByPrimaryKeySelective(crowdFunding);
    }

    @Override
    public ArrayList<CrowdFunding> getCfs(CrowdFunding crowdFunding,String orderCondition,String orderType) {
        return crowdFundingMapper.getCfs(crowdFunding,orderCondition, orderType);
    }
}
