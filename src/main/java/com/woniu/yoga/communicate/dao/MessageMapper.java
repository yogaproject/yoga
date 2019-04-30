package com.woniu.yoga.communicate.dao;

import com.woniu.yoga.communicate.pojo.Message;

import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Long msgId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Long msgId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    List<Message> getUnreadMsgsByUserId(Integer userId);
}