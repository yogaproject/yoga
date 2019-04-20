package com.woniu.yoga.user.pojo;

import javax.persistence.*;

//@Entity
//@Table
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "comment_detail")
    private String commentDetail;
    @Column(name = "entity_id")
    private Integer entityId;
    @Column(name = "entity_type")
    private Integer entityType;
    @Column(name = "comment_degree")
    private Integer commentDegree;
    @Column(name = "comment_flag")
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