package com.woniu.yoga.cf.dao;

import com.woniu.yoga.cf.pojo.CrowdFunding;

public interface CrowdFundingMapper {
    int deleteByPrimaryKey(Integer cfId);

    int insert(CrowdFunding record);

    int insertSelective(CrowdFunding record);

    CrowdFunding selectByPrimaryKey(Integer cfId);

    int updateByPrimaryKeySelective(CrowdFunding record);

    int updateByPrimaryKey(CrowdFunding record);
}