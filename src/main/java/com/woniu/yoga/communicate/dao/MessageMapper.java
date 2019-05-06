package com.woniu.yoga.communicate.dao;

import com.woniu.yoga.communicate.pojo.Message;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Long msgId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Long msgId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    List<Message> getUnreadMsgsByUserId(Integer userId);

    @Update("update message set msg_status = 1 where from_id = #{fromId} and to_id = #{toId}")
    int updateMsgStatusByFromIdAndToId(@Param("fromId") Integer fromId, @Param("toId") Integer toId);
}