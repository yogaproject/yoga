package com.woniu.yoga.user.controller;

import com.woniu.yoga.manage.pojo.Coupon;
import com.woniu.yoga.user.pojo.Order;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.service.serviceImpl.UserServiceImpl;
import com.woniu.yoga.user.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author liufeng
 * @ClassName StudentService
 * @Date 2019/4/18 15:30
 * @Version 1.0
 * @Description 用于处理用户与后台的交互
 **/
@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    /*
     * @Author liufeng
     * @Date
     * @Description //根据订单状态，查询用户订单
     * @Param
     *  String orderStatus：订单状态，默认为“所有订单”，只能为“未完成订单”，“已完成”，“所有订单”，其余为非法
     * @return
     *  返回查询到的订单结果集合，参数不合法返回空
     **/
    public Result listOrder(HttpSession session, String orderStatus) {
        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();
        return userService.listOrder(userId,orderStatus);
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //根据用户Id查找可用的优惠券
     * @Param
     *  Integer userId：用户id
     * @return
     *  返回可用优惠券的集合
     **/
    public Result listCouponsByUserId(Integer userId) {

        return userService.listCouponsByUserId(userId);
    }
}
