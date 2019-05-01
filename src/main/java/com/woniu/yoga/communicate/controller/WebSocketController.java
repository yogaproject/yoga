package com.woniu.yoga.communicate.controller;

import com.woniu.yoga.commom.enums.EntityTypeEnum;
import com.woniu.yoga.commom.utils.JsonUtil;
import com.woniu.yoga.communicate.pojo.Message;
import com.woniu.yoga.communicate.service.MessageService;
import com.woniu.yoga.user.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description websocket
 * @Author guochxi
 * @Date 2019/4/28 20:45
 * @Version 1.0
 */
@ServerEndpoint("/ws")
@Component
@Slf4j
public class WebSocketController {
    private static ApplicationContext applicationContext;
    //存放所有登录用户的Map集合，键：每个用户的唯一标识（用户Id）
    private static Map<Integer,WebSocketController> webSocketMap = new HashMap<>();
    //session作为用户建立连接的唯一会话，可以用来区别每个用户
    private Session session;
    //httpsession用以在建立连接的时候获取登录用户的唯一标识（登录名）,获取到之后以键值对的方式存在Map对象里面
    private static HttpSession httpSession;

    public static void setHttpSession(HttpSession httpSession){
        WebSocketController.httpSession=httpSession;
    }
    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }


    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        if(httpSession.getAttribute("user") == null){
            System.out.println("用户未登录");
            return;
        } else {
            Integer userId = ((User) httpSession.getAttribute("user")).getUserId();
            webSocketMap.put(userId,this);
            log.info("websocket有新的连接，总数：+{}",webSocketMap.size());
            //发送离线保存的未读消息
            MessageService messageService = applicationContext.getBean(MessageService.class);
            List<Message> messages = messageService.getUnreadMsgs(userId);
            if(messages != null){
                for(Message message : messages){
                    this.sendMessage(JsonUtil.toJson(message));
                    //将message状态设为已读
                    messageService.updateMsgStatusByFromIdAndToId(message.getFromId(),message.getToId());
                }
            }
        }
    }

    @OnMessage
    public void onMessage(String message){
        log.info("收到客户端消息：{}",message);
        Message msg = null;
        try {
             msg = (Message) JsonUtil.toObject(message,Message.class);
            System.out.println(msg);
        }catch (Exception e){
            log.error("【对象转换异常】+{}",e.getMessage());
        }
        MessageService messageService = applicationContext.getBean(MessageService.class);
        for (Map.Entry<Integer,WebSocketController> entry : webSocketMap.entrySet()){
            if(msg.getToId().equals(entry.getKey())){
                //在线，将msg_status改为1？
                entry.getValue().sendMessage(JsonUtil.toJson(msg));
                msg.setMsgStatus(1);
                break;
            }
        }
        //将消息保存在数据库
        messageService.sendMessage(msg);
    }

    public void sendMessage(String message){
        log.info("发送消息+{}",message);
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(){
        for (Map.Entry<Integer,WebSocketController> entry  : webSocketMap.entrySet()) {
            if(entry.getValue().session==this.session){
                webSocketMap.remove(entry.getKey());
                log.info("websocket连接断开，总数：{}",webSocketMap.size());
                break;
            }
        }
    }

    @OnError
    public void onError(Session session,Throwable error){
        error.printStackTrace();
        log.error("websocket异常",error.getMessage());
    }
}
