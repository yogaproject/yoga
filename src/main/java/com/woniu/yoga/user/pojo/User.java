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

    private String userPwd;

    private String salt;

    private Integer roleId;

    private String userPhone;

    private String userEmail;

    private String userWechat;

    private String userQq;

    private String userNickname;

    private String userHeadimg;

    private double longitude;

    private double latitude;

    private String userLocation;

    private String tempName;

    private String realName;

    private String idcard;

    private Integer userLevel;

    private Integer userScore;

    private Integer userPrivacy;
    @Column(nullable=false,name = "active",columnDefinition = "int default 0")
    private Integer active;

    private String userVerifyCode;

    private Integer userFlag;

}