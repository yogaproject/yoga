package com.woniu.yoga.communicate.service.impl;

import com.woniu.yoga.communicate.repository.MessageRepository;
import com.woniu.yoga.communicate.pojo.Message;
import com.woniu.yoga.communicate.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    private MessageRepository messageRepository;
    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Map<String, Object> findByFromId(Integer fromId) {
        return null;
    }
}
