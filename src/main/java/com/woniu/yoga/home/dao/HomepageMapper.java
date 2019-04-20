package com.woniu.yoga.home.dao;

import com.woniu.yoga.home.pojo.Homepage;

public interface HomepageMapper {
    int deleteByPrimaryKey(Integer mId);

    int insert(Homepage record);

    int insertSelective(Homepage record);

    Homepage selectByPrimaryKey(Integer mId);

    int updateByPrimaryKeySelective(Homepage record);

    int updateByPrimaryKeyWithBLOBs(Homepage record);

    int updateByPrimaryKey(Homepage record);
}