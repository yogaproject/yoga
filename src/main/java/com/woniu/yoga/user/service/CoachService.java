package com.woniu.yoga.user.service;

import com.woniu.yoga.user.pojo.Coach;
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
    Result updateOrder(Integer orderId, String result);
    /*
     * @Author liufeng
     * @Date
     * @Description //查看学员信息？？？？？
     * @Param
     * @return
     **/
    List<StudentDetailVO> listStudentByCoachId(Integer coachId);
    /*
     * @Author liufeng
     * @Date
     * @Description //查找所有流派
     * @Param
     * @return
     **/
    List listCoachStyles();











}