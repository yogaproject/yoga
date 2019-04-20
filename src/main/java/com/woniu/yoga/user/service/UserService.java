package com.woniu.yoga.user.service;

import com.woniu.yoga.user.pojo.Coupon;
import com.woniu.yoga.user.pojo.Order;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author liufeng
 * @ClassName StudentService
 * @Date 2019/4/18 15:30
 * @Version 1.0
 * @Description TODO
 **/
public interface UserService {

    /*
     * @Author liufeng
     * @Date
     * @Description //根据条件查询订单
     * @Param
     * @return
     **/
    List<Order> listOrder(Integer userId, @RequestParam(required = false, defaultValue = "所有订单") String orderStatus);

    /*
     * @Author liufeng
     * @Date
     * @Description //根据用户id查找有效的订单
     * @Param
     * @return
     **/
    List<Coupon> listCouponsByUserId(Integer userId);
}
