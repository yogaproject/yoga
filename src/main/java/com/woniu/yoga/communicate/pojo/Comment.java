package com.woniu.yoga.communicate.pojo;

import lombok.Data;

@Data
public class Comment {
    private Integer commentId;

    private Integer userId;

    private String commentDetail;

    private Integer entityId;

    private Integer entityType;

    private Integer commentDegree;

    private Integer commentFlag;

}