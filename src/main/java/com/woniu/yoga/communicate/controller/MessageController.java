package com.woniu.yoga.communicate.controller;

import com.woniu.yoga.commom.exception.YogaException;
import com.woniu.yoga.commom.utils.JsonUtil;
import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.communicate.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description 私信通知controller层
 * @Author guochxi
 * @Date 22:03 2019/4/22
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/msg")
@Slf4j
public class MessageController {

    @Autowired
    public MessageService messageService;

    @GetMapping(value = "/{conversationId}/detail")
    public String getConversationDetail(@PathVariable("conversationId") String conversationId){
        try {
            System.out.println("msg detail*****");
            Map<String,Object> details = messageService.getConversationDetail(conversationId);
            return JsonUtil.toJson(details);
        } catch (YogaException e){
            log.error("内部错误。conversationId={}",conversationId);
            return JsonUtil.toJson(Result.error("查询错误"));
        }
    }
}
