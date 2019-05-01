package com.woniu.yoga.home.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author huijie yan
 * @version 1.0.0
 * @description 主页图文
 * @date 2019/4/22 15:31
 */
@Data
@ApiModel("动态")
public class HomepageVo {
    @ApiModelProperty("动态内容id")
    private Integer mId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("图片")
    private String img;

    @ApiModelProperty("动态内容id")
    private Integer userId;

    @ApiModelProperty("评论数量")
    private Long commentCount;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("发布时间")
    private String publishTime;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("显示距离")
    private Float distance;

    @ApiModelProperty("用户昵称")
    private String userNickName;

    @ApiModelProperty("user等级")
    private Integer userLevel;

    @ApiModelProperty("用户设置的权限")
    private Integer userPrivacy;

    @ApiModelProperty("动态图文设置的权限")
    private Integer homepagePrivacy;

    @ApiModelProperty("关注状态")
    private Integer followStatus;
}
