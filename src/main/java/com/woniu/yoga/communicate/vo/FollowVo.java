package com.woniu.yoga.communicate.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author huijie yan
 * @version 1.0.0
 * @description 关注列表视图类
 * @date 2019/4/24 16:02
 */
@Data
public class FollowVo {
    private Integer followId;

    private Integer userId;

    private Integer followedId;

    private Date createTime;

    private Integer followStatus;

    private Integer userLevel;

    private String userHeadimg;

    private String userNickName;
}
