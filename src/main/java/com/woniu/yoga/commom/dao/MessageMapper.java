package com.woniu.yoga.commom.dao;

import com.woniu.yoga.commom.pojo.Message;

public interface MessageMapper {
    int deleteByPrimaryKey(Long msgId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Long msgId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}