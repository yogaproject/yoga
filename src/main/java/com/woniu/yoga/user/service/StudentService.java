package com.woniu.yoga.user.service;

import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.communicate.pojo.Comment;
import com.woniu.yoga.user.pojo.Student;
import com.woniu.yoga.user.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author liufeng
 * @ClassName StudentService
 * @Date 2019/4/18 15:30
 * @Version 1.0
 * @Description
 **/
@Service
@Transactional
public interface StudentService {
    Student findStudentByUserId(Integer userId);

    void saveStudent(Student student);







    Result saveOrder(Integer userId, OrderVO orderVO) throws RuntimeException;


    Result updateOrderWithCoupon(Integer userId,String orderId, @RequestParam(required = false) Integer couponId) throws RuntimeException;


    Result updateOrderForPay(Integer userId,String orderId) throws RuntimeException;


    Result saveComment(Integer userId,String orderId,Comment comment) throws RuntimeException;


    Result listAllCourseAppoint() throws RuntimeException;


    Result updateOrderForRefund(Integer userId,String orderId) throws RuntimeException;

    Result updateOrderForCancel(Integer userId, String orderId) throws RuntimeException;

    Result findCoachPhoneByUserId(Integer userId) throws RuntimeException;

    Result repeatOrder(Integer userId, String orderId) throws RuntimeException;
}
