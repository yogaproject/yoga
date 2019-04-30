package com.woniu.yoga.user.service;

import com.woniu.yoga.communicate.pojo.Comment;
import com.woniu.yoga.user.pojo.Order;
import com.woniu.yoga.user.vo.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author liufeng
 * @ClassName StudentService
 * @Date 2019/4/18 15:30
 * @Version 1.0
 * @Description TODO
 **/
public interface StudentService {

    Result listAroundUserByAddress(SearchConditionVO searchConditionVO);


    Result getDetailInfoByUserId(Integer userId, Integer coachId);


    Result saveOrder(Integer userId,Order order);


    Result updateOrderWithCoupon(Integer userId,String orderId, @RequestParam(required = false) Integer couponId);


    Result updateOrderForPay(Integer userId,String orderId);


    Result saveComment(Integer userId,String orderId,Comment comment);


    Result listAllCourseAppoint();


    Result updateOrderForRefund(Integer userId,String orderId);

    Result updateOrderForCancel(Integer userId, String orderId);

    Result findCoachPhoneByUserId(Integer userId);

    Result repeatOrder(Integer userId, String orderId);
}
