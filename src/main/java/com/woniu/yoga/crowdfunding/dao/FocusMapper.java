package com.woniu.yoga.crowdfunding.dao;

import com.woniu.yoga.crowdfunding.pojo.Focus;

public interface FocusMapper {
    int deleteByPrimaryKey(Integer cfFocId);

    int insert(Focus record);

    int insertSelective(Focus record);

    Focus selectByPrimaryKey(Integer cfFocId);

    int updateByPrimaryKeySelective(Focus record);

    int updateByPrimaryKey(Focus record);
}