package com.woniu.yoga.user.service.serviceImpl;

import com.woniu.yoga.user.pojo.Coupon;
import com.woniu.yoga.user.pojo.Order;
import com.woniu.yoga.user.service.UserService;

import java.util.List;

/**
 * @Author liufeng
 * @ClassName StudentService
 * @Date 2019/4/18 15:30
 * @Version 1.0
 * @Description TODO
 **/
public class UserServiceImpl implements UserService {
    //查询用户所有订单
    @Override
    public List<Order> listOrder(Integer userId, String orderStatus) {

        return null;
    }
    //查询用户所有有效的优惠券
    @Override
    public List<Coupon> listCouponsByUserId(Integer userId) {

        return null;
    }
}
