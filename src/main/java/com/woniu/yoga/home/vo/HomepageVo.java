package com.woniu.yoga.home.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author huijie yan
 * @version 1.0.0
 * @description 主页图文
 * @date 2019/4/22 15:31
 */
@Data
public class HomepageVo {
    //动态内容id
    private Integer mId;
    //标题
    private String title;
    //图片
    private String img;
    //用户id
    private Integer userId;
    //评论数量
    private Long commentCount;
    //创建时间
    private Date createTime;
    //发布时间
    private String publishTime;
    //内容
    private String content;
    //显示距离
    private Float distance;
    //用户昵称
    private String userNickName;
    //user等级
    private Integer userLevel;
    //用户设置的权限
    private Integer userPrivacy;
    //动态图文设置的权限
    private Integer homepagePrivacy;
    //关注状态
    private Integer followStatus;
}
