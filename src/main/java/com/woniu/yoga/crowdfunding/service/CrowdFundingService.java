package com.woniu.yoga.crowdfunding.service;

import com.woniu.yoga.crowdfunding.pojo.CrowdFunding;

import java.util.ArrayList;

public interface CrowdFundingService {

    CrowdFunding selCfById(Integer cfId);

    void updateStatus(CrowdFunding crowdFunding);

    void updateCf(CrowdFunding crowdFunding);

    ArrayList<CrowdFunding> getCfs(CrowdFunding crowdFunding, String orderCondition,String orderType);
}
