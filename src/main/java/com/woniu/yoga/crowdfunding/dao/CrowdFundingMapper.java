package com.woniu.yoga.crowdfunding.dao;

import com.woniu.yoga.crowdfunding.pojo.CrowdFunding;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface CrowdFundingMapper {
    int deleteByPrimaryKey(Integer cfId);

    int insert(CrowdFunding record);

    int insertSelective(CrowdFunding record);

    CrowdFunding selectByPrimaryKey(Integer cfId);

    int updateByPrimaryKeySelective(CrowdFunding record);

    int updateByPrimaryKey(CrowdFunding record);

    ArrayList<CrowdFunding> getCfs(CrowdFunding crowdFunding,@Param("orderCondition") String orderCondition,@Param("orderType") String orderType);
}