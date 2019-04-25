package com.woniu.yoga.user.vo;

import com.woniu.yoga.user.pojo.Course;

import java.util.List;

/**
 * @Author liufeng
 * @ClassName CoachDetailInfoVO
 * @Date 2019/4/22 11:32
 * @Version 1.0
 * @Description 保存瑜伽师个人的详细信息
 **/
public class CoachDetailInfoVO {
    //    真实姓名
    private String realName;
    //    头像
    private String headImg;
    //    电话号码
    private String phone;
    //    QQ号码
    private String qq;
    //    微信号
    private String wechat;
    //    简介
    private String detail;
    //    流派
    private String style;
    //    认证方式 0未认证（默认），1 场馆认证（签约场馆），2官方认证
    private byte authentication;
    //    课程
    private List<Course> courses;
    //    交易数量
    private int numberOfTrade;
    //    好评数
    private int goodCommentCount;
    //    场馆名
    private String venueName;

    //是否保密
    private int privacy;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public byte getAuthentication() {
        return authentication;
    }

    public void setAuthentication(byte authentication) {
        this.authentication = authentication;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public int getNumberOfTrade() {
        return numberOfTrade;
    }

    public void setNumberOfTrade(int numberOfTrade) {
        this.numberOfTrade = numberOfTrade;
    }

    public int getGoodCommentCount() {
        return goodCommentCount;
    }

    public void setGoodCommentCount(int goodCommentCount) {
        this.goodCommentCount = goodCommentCount;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }

    @Override
    public String toString() {
        return "{" +
                "realName:'" + realName + '\'' +
                ", headImg:'" + headImg + '\'' +
                ", phone:'" + phone + '\'' +
                ", qq:'" + qq + '\'' +
                ", wechat:'" + wechat + '\'' +
                ", detail:'" + detail + '\'' +
                ", style:'" + style + '\'' +
                ", authentication:" + authentication +
                ", courses:" + courses +
                ", numberOfTrade:" + numberOfTrade +
                ", goodCommentCount:" + goodCommentCount +
                ", venueName:'" + venueName + '\'' +
                '}';
    }
}
