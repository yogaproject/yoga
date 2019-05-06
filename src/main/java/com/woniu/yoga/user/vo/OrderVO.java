package com.woniu.yoga.user.vo;

import com.woniu.yoga.user.pojo.Course;
import com.woniu.yoga.user.pojo.User;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description TODO
 * @Author Administrator
 * @Date 2019/5/5 9:47
 * @Version 1.0
 */
@Data
public class OrderVO {
    private String orderId;
    //支付方的userId
    private UserVO payer;
    //接收方的userId
    private Integer accepterId;
    private UserVO accepter;
    //包含的课程
    private Course course;
    //订单金额
    private BigDecimal orderMoney;
    //订单状态
    private String orderStatus;

    private Integer courseId;

    private Integer payStatus;
    //课时数
    private Integer courseCount;
    //更新时间
    private Date updateTime;
    //创建时间
    private Date createTime;
    //优惠券编号
    private Integer couponId;
    //付款金额
    private BigDecimal discount;
    //是否删除
    private Integer orderFlag;
}
