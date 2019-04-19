package com.woniu.yoga.venue.dao;

import com.woniu.yoga.venue.pojo.CC;

public interface CCMapper {
    int deleteByPrimaryKey(Integer ccId);

    int insert(CC record);

    int insertSelective(CC record);

    CC selectByPrimaryKey(Integer ccId);

    int updateByPrimaryKeySelective(CC record);

    int updateByPrimaryKey(CC record);
}