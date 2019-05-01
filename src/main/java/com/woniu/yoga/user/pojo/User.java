package com.woniu.yoga.user.pojo;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
* @Description:    java类作用描述
 * 修改 设置active 默认值为0，加@data注解
* @Author:         lxy
* @CreateDate:     2019/4/23 9:50
*/
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Proxy(lazy = false)
@Table(name = "user")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer userId;
    @Column(name = "user_pwd")
    private String userPwd;
    @Column
    private String salt;
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "user_phone")
    private String userPhone;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "user_wechat")
    private String userWechat;
    @Column(name = "user_qq")
    private String userQq;
    @Column(name = "user_nickname")
    private String userNickname;
    @Column(name = "user_headimg")
    private String userHeadimg;
    @Column
    private String sex;
    @Column
    private Float longitude;
    @Column
    private Float latitude;
    @Column(name = "user_location")
    private String userLocation;
    @Column(name = "temp_name")
    private String tempName;
    @Column(name = "real_name")
    private String realName;
    @Column
    private String idcard;
    @Column(name = "user_level",columnDefinition = "int default 0")
    private Integer userLevel;
    @Column(name = "user_score",columnDefinition = "int default 0")
    private Integer userScore;
    @Column(name = "user_privacy",columnDefinition = "int default 0")
    private Integer userPrivacy;
    @Column(nullable=false,name = "active",columnDefinition = "int default 0")
    private Integer active;
    @Column(name = "user_verify_code")
    private String userVerifyCode;
    @Column(name = "user_flag",columnDefinition = "int default 0")
    private Integer userFlag;


}