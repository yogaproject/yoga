package com.woniu.yoga.crowdfunding.dao;

import com.woniu.yoga.crowdfunding.pojo.Supporter;

public interface SupporterMapper {
    int deleteByPrimaryKey(Integer supId);

    int insert(Supporter record);

    int insertSelective(Supporter record);

    Supporter selectByPrimaryKey(Integer supId);

    int updateByPrimaryKeySelective(Supporter record);

    int updateByPrimaryKey(Supporter record);
}