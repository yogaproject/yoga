package com.woniu.yoga.communicate.service;

import com.woniu.yoga.communicate.pojo.Message;

import java.util.List;
import java.util.Map;

/**
 * @Description 消息通知业务接口
 * @Author guochxi
 * @Date 2019/4/20 21:25
 * @Version 1.0
 */
public interface MessageService {
    /**
     * @Description 发送消息
     * @Author guochxi
     * @Date 17:59 2019/4/22
     * @Param [message]
     * @return com.gcx.jap_mybatis_test.entity.Message
     **/
    Message sendMessage(Message message);

    /**
     * @Description 查找userId的所有消息并按时间排序
     * @Author guochxi
     * @Date 18:47 2019/4/22
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    List<Message> getConversationListByUserIdAndEntityType(Integer userId,Integer entityType);

    /**
     * @Description 根据会话id查询消息详情
     * @Author guochxi
     * @Date 19:41 2019/4/22
     * @Param [conversationId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    List<Message> getConversationDetail(Integer fromId,Integer toId);

    /**
     * @Description 将fromId的消息状态设为已读
     * @Author guochxi
     * @Date 18:01 2019/4/22
     * @Param [fromId, toId]
     * @return int
     **/
    int updateMsgStatusByFromIdAndToId(Integer fromId,Integer toId);

    /**
     * @Description 删除与fromId的消息
     * @Author guochxi
     * @Date 18:02 2019/4/22
     * @Param [fromId, toId]
     * @return int
     **/
    int delMessageByFromIdAndToId(Integer fromId,Integer toId);

    /**
     * @Description 查找与某人聊天的未读消息个数
     * @Author guochxi
     * @Date 15:11 2019/4/23
     * @Param [fromId, toId]
     * @return
     **/
    Integer getUnreadCount(Integer userId,String conversationId);

    /**
     * @Description 根据用户Id获取未读的所有消息
     * @Author guochxi
     * @Date 14:30 2019/4/30
     * @Param [userId]
     * @return java.util.List<com.woniu.yoga.communicate.pojo.Message>
     **/
    List<Message> getUnreadMsgs(Integer userId);
}
