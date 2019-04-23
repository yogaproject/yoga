package com.woniu.yoga.communicate.service.impl;

import com.woniu.yoga.communicate.repository.MessageRepository;
import com.woniu.yoga.communicate.pojo.Message;
import com.woniu.yoga.communicate.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Description 私信通知业务实现类
 * @Author Administrator
 * @Date 2019/4/20 21:41
 * @Version 1.0
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    @Transactional
    public int updateMsgStatusByFromIdAndToId(Integer fromId, Integer toId) {
        return messageRepository.updateMsgStatusByFromIdAndToId(fromId,toId);
    }

    @Override
    @Transactional
    public int delMessageByFromIdAndToId(Integer fromId, Integer toId) {
        return messageRepository.updateMsgFlagByFromIdAndToId(fromId,toId);
    }

    @Override
    public Message sendMessage(Message message) {

        return null;
    }

    @Override
    public Map<String, Object> findByToid() {
        return null;
    }

    @Override
    public Map<String, Object> getConversationDetail(String conversationId) {
        return null;
    }
}
