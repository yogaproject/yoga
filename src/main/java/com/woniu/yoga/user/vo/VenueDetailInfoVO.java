package com.woniu.yoga.user.vo;

import com.woniu.yoga.venue.pojo.Venue;
import lombok.Data;

import javax.persistence.Column;

/**
 * @Author liufeng
 * @ClassName VenueDetailInfoVO
 * @Date 2019/5/5 18:49
 * @Version 1.0
 * @Description 场馆的详细信息
 **/
@Data
public class VenueDetailInfoVO {
    private Integer userId;
    private String userPwd;
    private String salt;
    private Integer roleId;
    private String userPhone;
    private String userEmail;
    private String userWechat;
    private String userQq;
    private String userNickname;
    private String userHeadimg;
    private String sex;
    private Float longitude;
    private Float latitude;
    private String userLocation;
    private String realName;
    private Integer userLevel;
    private Integer userScore;
    private Integer active;
    private Long clicks;
    private String img1;
    private String img2;
    private String img3;
    private String venueDetail;
    //我的关注
    private int focus;
    //我的粉丝
    private int fans;
    //我的动态
    private int info;
    //我的评论
    private int comments;

}
