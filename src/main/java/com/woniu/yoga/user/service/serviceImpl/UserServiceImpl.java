package com.woniu.yoga.user.service.serviceImpl;

import com.woniu.yoga.manage.dao.CouponMapper;
import com.woniu.yoga.manage.pojo.Coupon;
import com.woniu.yoga.user.dao.OrderMapper;
import com.woniu.yoga.user.dao.UserMapper;
import com.woniu.yoga.user.pojo.Order;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.service.UserService;
import com.woniu.yoga.user.util.*;
import com.woniu.yoga.user.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;


/**
 * @Author liufeng
 * @ClassName StudentService
 * @Date 2019/4/18 15:30
 * @Version 1.0
 * @Description TODO
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CouponMapper couponMapper;

    //查询用户所有订单
    @Override
    public Result listOrder(Integer userId, String orderStatus) throws RuntimeException{
        String status = null;
        if (orderStatus != null) {
            status = OrderUtil.getOrderStatus(orderStatus);//利用工具将状态转为字符串（数组）
        }
        List<Order> data = null;
        try {
            data = orderMapper.findOrderByUserIdAndStatus(userId, orderStatus);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return ResultUtil.actionSuccess("查询成功",data);
    }

    //查询用户所有有效的优惠券
    @Override
    public Result listCouponsByUserId(Integer userId) throws RuntimeException{
        try {
            List<Coupon> coupons = couponMapper.selectByUserId(userId);
            return ResultUtil.actionSuccess("查询成功",coupons);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
