package com.woniu.yoga.user.service.serviceImpl;

import com.woniu.yoga.manage.pojo.Coupon;
import com.woniu.yoga.user.dao.OrderMapper;
import com.woniu.yoga.user.pojo.Order;
import com.woniu.yoga.user.service.UserService;
import com.woniu.yoga.user.util.OrderUtil;
import com.woniu.yoga.user.util.ResultUtil;
import com.woniu.yoga.user.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    //查询用户所有订单
    @Override
    public Result listOrder(Integer userId, String orderStatus) {
        int status = 0;
        if (orderStatus != null) {
            status = OrderUtil.checkOrderStatus(orderStatus);//利用工具将字符串状态转为数字状态
            if (status == -1) {
                return ResultUtil.errorOperation("此类订单不存在");
            }
        }
        List<Order> data =orderMapper.findOrderByUserIdAndStatus(userId, orderStatus);
        return ResultUtil.actionSuccess("查询成功",data);
    }

    //查询用户所有有效的优惠券
    @Override
    public Result listCouponsByUserId(Integer userId) {
        //等待工具中......
        return null;
    }
}
