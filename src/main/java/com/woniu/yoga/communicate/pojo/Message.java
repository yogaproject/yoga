package com.woniu.yoga.communicate.pojo;

import com.woniu.yoga.commom.enums.EntityTypeEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Description 通知消息实体类
 * @Author guochxi
 */
@Entity
@Data
public class Message {
    @Id
    private Long msgId;
    /**消息发送方*/
    private Integer fromId;
    /**消息接收方*/
    private Integer toId;
    /**消息内容*/
    private String content;
    /**查看消息附带的跳转链接*/
    private String msgUrl;
    /**消息发送时间*/
    private Date sendTime;
    /**消息类型*/
    private Integer entityType;
    /**会话id，A->B和B->A为同一个，较小的userId的在前，较大的userId在后*/
    private String conversationId;
    /**消息状态，0（未读，默认），1（已读）*/
    private Integer msgStatus;
    /**软删除*/
    private Integer msgFlag;

    public Message(Integer fromId,Integer toId,String content,Integer entityType){
        this.fromId = fromId;
        this.toId = toId;
        this.content = content;
        this.entityType = entityType;
        this.conversationId = this.getConversationId();
    }

    public String getConversationId() {
        return fromId < toId ? fromId + "_" + toId : toId + "_" + fromId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public void setConversationId(Integer fromId, Integer toId){
        String conId = fromId < toId ? fromId + "_" + toId : toId + "_" + fromId;
        this.setConversationId(conId);
    }
}