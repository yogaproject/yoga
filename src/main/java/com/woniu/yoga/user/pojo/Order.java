package com.woniu.yoga.user.pojo;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {
    //订单编号
    private String orderId;
    //支付方的userId
    private Integer payerId;
    //接收方的userId
    private Integer accepterId;
    //包含的课程
    private Course course;
    //订单金额
    private BigDecimal orderMoney;
    //订单状态
    private Integer orderStatus;

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