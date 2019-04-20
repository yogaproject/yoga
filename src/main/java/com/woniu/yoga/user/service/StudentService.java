package com.woniu.yoga.user.service;

import com.woniu.yoga.user.pojo.Comment;
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

    List<UserVO> listAroundUserByIdOrAddress(SearchConditionVO searchConditionVO);


    UserDetailInfoVo getDetailInfoByUserId(Integer userId);


    Result saveOrder(Order order);


    Result updateOrderWithCoupon(String orderId, @RequestParam(required = false) Integer couponId);


    Result updateOrderForPay(Integer orderId);


    Comment saveComment(Comment comment);


    List<CourseAppoint> listAllCourseAppoint();


}
