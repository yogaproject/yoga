package com.woniu.yoga.home.pojo;

import java.util.Date;

public class Homepage {
    private Integer mId;

    private String title;

    private String img;

    private Integer userId;

    private Long commentCount;

    private Date createTime;

    private Integer homepageFlag;

    private String content;

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getHomepageFlag() {
        return homepageFlag;
    }

    public void setHomepageFlag(Integer homepageFlag) {
        this.homepageFlag = homepageFlag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}