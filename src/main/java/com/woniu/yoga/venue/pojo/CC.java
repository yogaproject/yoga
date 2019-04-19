package com.woniu.yoga.venue.pojo;

import java.util.Date;

public class CC {
    private Integer ccId;

    private Integer courseId;

    private Integer studentId;

    private String commentDetail;

    private Date createTime;

    private Integer commentDegree;

    private Integer ccFlag;

    public Integer getCcId() {
        return ccId;
    }

    public void setCcId(Integer ccId) {
        this.ccId = ccId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getCommentDetail() {
        return commentDetail;
    }

    public void setCommentDetail(String commentDetail) {
        this.commentDetail = commentDetail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCommentDegree() {
        return commentDegree;
    }

    public void setCommentDegree(Integer commentDegree) {
        this.commentDegree = commentDegree;
    }

    public Integer getCcFlag() {
        return ccFlag;
    }

    public void setCcFlag(Integer ccFlag) {
        this.ccFlag = ccFlag;
    }
}