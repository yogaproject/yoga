package com.woniu.yoga.communicate.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author huijie yan
 * @version 1.0.0
 * @description 关注列表视图类
 * @date 2019/4/24 16:02
 */
@Data
@ApiModel("关注人")
public class FollowVo {
    @ApiModelProperty("评论id")
    private Integer followId;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("关注人的用户id")
    private Integer followedId;

    @ApiModelProperty("关注时间")
    private Date createTime;

    @ApiModelProperty("关注状态")
    private Integer followStatus;

    @ApiModelProperty("用户等级")
    private Integer userLevel;

    @ApiModelProperty("用户头像")
    private String userHeadimg;

    @ApiModelProperty("用户昵称")
    private String userNickName;
}
