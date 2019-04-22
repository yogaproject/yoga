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

    Message save(Message message);

    Map<String,Object> findByFromId(Integer fromId);


}
