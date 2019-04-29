package com.woniu.yoga.communicate.vo;

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
public class CommentVo {
    private Integer commentId;

    private Integer userId;

    private String commentDetail;

    private Integer entityId;

    private Integer entityType;

    private Integer commentDegree;

    private Date commentCreateTime;

    private String publishTime;

    private String userNickName;

    private String userHeadimg;

    private List<CommentVo> children;

    private String roleName;
}
