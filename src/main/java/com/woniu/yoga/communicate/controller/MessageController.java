package com.woniu.yoga.communicate.controller;

import com.woniu.yoga.commom.exception.YogaException;
import com.woniu.yoga.commom.utils.JsonUtil;
import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.communicate.pojo.Message;
import com.woniu.yoga.communicate.service.MessageService;
import com.woniu.yoga.communicate.vo.ViewObject;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
@Api(value = "MessageController|私信通知controller")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    /**
     * @Description 发送消息
     * @Author guochxi
     * @Date 15:49 2019/4/23
     * @Param [message]
     * @return java.lang.String
     **/
    @ApiOperation(value = "发送消息",notes = "根据消息对象发送消息")
//    @ApiImplicitParam(name = "message",value = "消息对象",required = true,dataType = "Message")
    @PostMapping("/sendMsg")
    public String sendMessage(Message message){
        try {
            messageService.sendMessage(message);
            return JsonUtil.toJson(Result.success("发送成功！"));
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtil.toJson(Result.error("发送失败！"));
        }
    }

    /**
     * @Description 查询消息详情
     * @Author guochxi
     * @Date 13:11 2019/4/23
     * @Param [conversationId]
     * @return java.lang.String
     **/
    @ApiOperation(value = "查询fromId和toId的消息详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fromId",value = "发送方Id",required = true,paramType = "path"),
            @ApiImplicitParam(name = "toId",value = "接收方Id",required = true,paramType = "path")
    })
    @GetMapping(value = "/{fromId}/{toId}/detail")
    public String getConversationDetail(@PathVariable("fromId") Integer fromId,
                                        @PathVariable("toId") Integer toId){
        try {
            System.out.println("msg detail*****");
            List<Message> msgList = messageService.getConversationDetail(fromId,toId);
            return JsonUtil.toJson(msgList);
        } catch (YogaException e){
            log.error("内部错误。fromId={},toId={}",fromId,toId);
            return JsonUtil.toJson(Result.error("查询错误"));
        }
    }

    /**
     * @Description 根据消息类型获取用户的消息列表
     * @Author guochxi
     * @Date 15:29 2019/4/23
     * @Param [entityType, httpSession]
     * @return java.lang.String
     **/
    @ApiOperation(value = "根据消息类型获取用户的消息列表")
    @ApiImplicitParam(name = "entityType",value = "消息类别：27私信；28通知",required = true,paramType = "path")
    @GetMapping(value = "/{entityType}/list")
    public String getConversationList(@PathVariable("entityType") Integer entityType,HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if(user == null){
            return JsonUtil.toJson(Result.error("您还未登录！"));
        }
        List<Message> conversationList = messageService.getConversationListByUserIdAndEntityType(user.getUserId(),entityType);
        List<ViewObject> list = new ArrayList<>();
        for (Message message : conversationList) {
            ViewObject viewObject = new ViewObject();
            viewObject.set("message",message);
            Integer targetId = message.getFromId() == user.getUserId() ? message.getToId() : message.getFromId();
            //viewObject.set("targetUser",userService.getUserById(targetId));
            Integer unread = messageService.getUnreadCount(user.getUserId(),message.getConversationId());
            viewObject.set("unread",unread);
            list.add(viewObject);
        }
        return JsonUtil.toJson(list);
    }

    /**
     * @Description 删除与某人的会话
     * @Author guochxi
     * @Date 16:14 2019/4/23
     * @Param [fromId, toId]
     * @return java.lang.String
     **/
    @ApiOperation(value = "删除与某人的会话")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fromId",value = "发送方Id",required = true,paramType = "path"),
            @ApiImplicitParam(name = "toId",value = "接收方Id",required = true,paramType = "path")
    })
    @DeleteMapping(value = "/{fromId}/{toId}/delete")
    public String delConversation(@PathVariable("fromId") Integer fromId,
                                  @PathVariable("toId") Integer toId){
        Integer count = messageService.delMessageByFromIdAndToId(fromId, toId);
        if(count > 0 ){
            return JsonUtil.toJson(Result.success("删除会话成功！"));
        }else {
            return JsonUtil.toJson(Result.error("删除失败！"));
        }
    }

    /**
     * @Description 将与某人的会话设置为已读
     * @Author guochxi
     * @Date 16:14 2019/4/23
     * @Param [fromId, toId]
     * @return java.lang.String
     **/
    @ApiOperation(value = "将与某人的会话设置为已读")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fromId",value = "发送方Id",required = true,paramType = "path"),
            @ApiImplicitParam(name = "toId",value = "接收方Id",required = true,paramType = "path")
    })
    @PutMapping(value = "/{fromId}/{toId}/updateStatus")
    public String updateMsgStatus(@PathVariable("fromId") Integer fromId,
                                  @PathVariable("toId") Integer toId){
        Integer count = messageService.updateMsgStatusByFromIdAndToId(fromId, toId);
        if(count > 0 ){
            return JsonUtil.toJson(Result.success("成功！"));
        }else {
            return JsonUtil.toJson(Result.error("失败！"));
        }
    }
}
