package com.woniu.yoga.user.service;

import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.user.pojo.Coach;
import com.woniu.yoga.user.pojo.Course;
import com.woniu.yoga.venue.pojo.Recruitment;
import com.woniu.yoga.venue.pojo.Venue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface CoachService {
    //根据教练id，查询教练详细信息
    Coach findCoachByCoachId(Integer coachId) throws RuntimeException;

    /*
     * @Author liufeng
     * @Description //瑜伽师处理带处理订单
     **/
    Result updateOrder(String orderId, String result) throws RuntimeException;
    /*
     * @Author liufeng
     * @Description //查看学员信息？？？？？
     **/
    Result listStudentByCoachId(Integer userId) throws RuntimeException;
    /*
     * @Author liufeng
     * @Description //查找所有流派
     **/
    Result listCoachStyles() throws RuntimeException;
    //教练根据查询条件查询场馆list
    List<Venue> findVenueByConditions(Recruitment recruitment) throws RuntimeException;


    /*
     * @Author liufeng
     * @Description //课程完结后，瑜伽师修改订单状态为待付款
     **/
    Result updateOrderForWaitToPay(Integer userId,String orderId) throws RuntimeException;
    /*
     * @Author liufeng
     * @Description //瑜伽师新建课程
     **/
    Result insertCourse(int userId,Course course) throws RuntimeException;
    /*
     * @Author liufeng
     * @Description //瑜伽师申请向场馆签约
     **/
    Result applyForSign(int userId, Integer venueId) throws RuntimeException;

    Result dealVenueRequest(int userId, Integer venueId, String result) throws RuntimeException;

    Result cancelContract(int userId) throws RuntimeException;

    Result deleteCourse(int userId, Integer courseId) throws RuntimeException;

    Result updateCourse(int userId, Course course) throws RuntimeException;




    void saveCoach(Coach coach);

    Coach findCoachByUserId(Integer userId);

    Integer updateCoach(Coach coach);
}
