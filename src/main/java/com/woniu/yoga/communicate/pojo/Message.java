package com.woniu.yoga.communicate.pojo;

import java.util.Date;

public class Message {
    private Long msgId;

    private Integer fromId;

    private Integer toId;

    private String content;

    private Date sendTime;

    private Integer msgStatus;

    private Integer msgFlag;

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(Integer msgStatus) {
        this.msgStatus = msgStatus;
    }

    public Integer getMsgFlag() {
        return msgFlag;
    }

    public void setMsgFlag(Integer msgFlag) {
        this.msgFlag = msgFlag;
    }
}