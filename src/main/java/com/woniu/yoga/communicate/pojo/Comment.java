package com.woniu.yoga.communicate.pojo;

public class Comment {
    private Long commentId;

    private Integer userId;

    private String commentDetail;

    private Integer entityId;

    private Integer entityType;

    private Integer commentDegree;

    private Integer commentFlag;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCommentDetail() {
        return commentDetail;
    }

    public void setCommentDetail(String commentDetail) {
        this.commentDetail = commentDetail;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Integer getEntityType() {
        return entityType;
    }

    public void setEntityType(Integer entityType) {
        this.entityType = entityType;
    }

    public Integer getCommentDegree() {
        return commentDegree;
    }

    public void setCommentDegree(Integer commentDegree) {
        this.commentDegree = commentDegree;
    }

    public Integer getCommentFlag() {
        return commentFlag;
    }

    public void setCommentFlag(Integer commentFlag) {
        this.commentFlag = commentFlag;
    }
}