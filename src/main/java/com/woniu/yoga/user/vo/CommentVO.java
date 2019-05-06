package com.woniu.yoga.user.vo;

import lombok.Data;

/**
 * @Description
 * @Author Administrator
 * @Date 2019/5/5 16:22
 * @Version 1.0
 */
@Data
public class CommentVO {
    private String orderId;
    private String comment;
    private int rate;
}
