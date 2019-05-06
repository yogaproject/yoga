package com.woniu.yoga.communicate.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author huijie yan
 * @version 1.0.0
 * @description 评论视图层
 * @date 2019/4/23 19:38
 */
@Data
@ApiModel("评论")
public class CommentVo {
    @ApiModelProperty("评论id")
    private Integer commentId;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("评论详情")
    private String commentDetail;

    @ApiModelProperty("评论实体id")
    private Integer entityId;

    @ApiModelProperty("评论类型:0为课程，1为homepage,2为回复评论")
    private Integer entityType;

    @ApiModelProperty("评论情况，仅课程评论需要有此字段：0好评，1中评，2差评")
    private Integer commentDegree;

    @ApiModelProperty("评论时间")
    private Date commentCreateTime;

    @ApiModelProperty("发布时间")
    private String publishTime;

    @ApiModelProperty("用户昵称")
    private String userNickName;

    @ApiModelProperty("用户头像")
    private String userHeadimg;

    @ApiModelProperty("评论角色")
    private String roleName;

    @ApiModelProperty("评论子节点")
    private List<CommentVo> children;
}
