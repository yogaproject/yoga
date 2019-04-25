package com.woniu.yoga.user.service;

import com.woniu.yoga.user.pojo.Coach;
import com.woniu.yoga.user.pojo.Course;
import com.woniu.yoga.venue.pojo.Recruitment;
import com.woniu.yoga.venue.pojo.Venue;
import com.woniu.yoga.user.vo.Result;
import com.woniu.yoga.user.vo.StudentDetailVO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CoachService {
    //根据教练id，查询教练详细信息
    Coach findCoachByCoachId(Integer coachId);

    /*
     * @Author liufeng
     * @Date
     * @Description //瑜伽师处理带处理订单
     * @Param
     * @return
     **/
    Result updateOrder(String orderId, String result);
    /*
     * @Author liufeng
     * @Date
     * @Description //查看学员信息？？？？？
     * @Param
     * @return
     **/
    Result listStudentByCoachId(Integer userId);
    /*
     * @Author liufeng
     * @Date
     * @Description //查找所有流派
     * @Param
     * @return
     **/
    Result listCoachStyles();
    //教练根据查询条件查询场馆list
    List<Venue> findVenueByConditions(Recruitment recruitment);



    Result updateOrderForWaitToPay(String orderId);

    Result insertCourse(int userId,Course course);
}
