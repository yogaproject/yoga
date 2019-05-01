package com.woniu.yoga.communicate.service.impl;

import com.woniu.yoga.commom.enums.ResultEnum;
import com.woniu.yoga.commom.exception.YogaException;
import com.woniu.yoga.communicate.dao.MessageMapper;
import com.woniu.yoga.communicate.repository.MessageRepository;
import com.woniu.yoga.communicate.pojo.Message;
import com.woniu.yoga.communicate.service.MessageService;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 私信通知业务实现类
 * @Author guochxi
 * @Date 2019/4/20 21:41
 * @Version 1.0
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageMapper messageMapper;

    @Override
    @Transactional
    public int updateMsgStatusByFromIdAndToId(Integer fromId, Integer toId) {
        return messageMapper.updateMsgStatusByFromIdAndToId(fromId,toId);
    }

    @Override
    @Transactional
    public int delMessageByFromIdAndToId(Integer fromId, Integer toId) {
        return messageRepository.updateMsgFlagByFromIdAndToId(fromId,toId);
    }

    @Override
    @Transactional
    public Integer sendMessage(Message message) {
        if(message == null || message.getFromId() == 0 || message.getToId() == 0 ||
                message.getContent() == null || "".equals(message.getContent())){
            throw new YogaException(ResultEnum.MSG_TYPE_ERROR);
        }
        message.setConversationId(message.getFromId(),message.getToId());
        return messageMapper.insert(message);
    }

    @Override
    public List<Message> getConversationListByUserIdAndEntityType(Integer userId, Integer entityType) {
        return messageRepository.findByUserIdAndEntityType(userId, entityType);
    }

    @Override
    @Transactional
    public List<Message> getConversationDetail(Integer fromId,Integer toId) {
        String conversationId = fromId < toId ? (fromId + "_" + toId): (toId + "_" + fromId);
        //查询消息详情
        List<Message> messages = messageRepository.findByConversationId(conversationId);
        //将消息设置为已读
        messageRepository.updateMsgStatusByFromIdAndToId(fromId,toId);
        return messages;
    }

    @Override
    public Integer getUnreadCount(Integer userId,String conversationId) {
        return messageRepository.getUnreadCount(userId,conversationId);
    }

    @Override
    public List<Message> getUnreadMsgs(Integer userId) {
        return messageMapper.getUnreadMsgsByUserId(userId);
    }
}
