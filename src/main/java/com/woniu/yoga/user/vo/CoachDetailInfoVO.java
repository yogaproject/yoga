package com.woniu.yoga.user.vo;

import com.woniu.yoga.user.pojo.Course;
import lombok.Data;

import java.util.List;

/**
 * @Author liufeng
 * @ClassName CoachDetailInfoVO
 * @Date 2019/4/22 11:32
 * @Version 1.0
 * @Description 保存瑜伽师个人的详细信息
 **/
@Data
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
//    private List<Course> courses;
    //    交易数量
    private int numberOfTrade;
    //    好评数
    private int goodCommentCount;
    //    场馆的ID
    private int venueId;
    //    场馆名
    private String venueName;
    //会员等级
    private byte level;

    //是否保密
    private int privacy;

}
