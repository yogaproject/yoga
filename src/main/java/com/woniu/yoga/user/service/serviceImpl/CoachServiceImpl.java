package com.woniu.yoga.user.service.serviceImpl;

import com.woniu.yoga.user.dao.CoachMapper;
import com.woniu.yoga.user.dao.CourseMapper;
import com.woniu.yoga.user.dao.OrderMapper;
import com.woniu.yoga.user.pojo.Coach;
import com.woniu.yoga.user.pojo.Course;
import com.woniu.yoga.user.pojo.Order;
import com.woniu.yoga.user.service.CoachService;
import com.woniu.yoga.user.util.OrderUtil;
import com.woniu.yoga.user.util.ResultUtil;
import com.woniu.yoga.user.vo.Result;
import com.woniu.yoga.user.vo.StudentVO;
import com.woniu.yoga.venue.pojo.Recruitment;
import com.woniu.yoga.venue.pojo.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {
    @Autowired
    private CoachMapper coachMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Coach findCoachByCoachId(Integer coachId) {
        Coach coach = coachMapper.selectByPrimaryKey(coachId);
        return coach;
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //瑜伽师传入待处理的订单
     * @Param
     * @return
     **/
    @Override
    public Result updateOrder(String orderId, String result) {
        //根据操作更改订单状态
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order.getOrderStatus() != OrderUtil.NEWORDER) {
            ResultUtil.illegalOperation();
        }
        int orderStatus = 0;
        if (result.equals("接受")) {
            orderStatus = OrderUtil.STARTORDER;
        } else if (result.equals("拒绝")) {
            orderStatus = OrderUtil.CANCELED;
        } else {
            ResultUtil.illegalOperation();
        }

        order.setOrderStatus(orderStatus);
        int row = orderMapper.updateByPrimaryKeySelective(order);
        if (row > 0) {
            List data = new ArrayList();
            data.add(order);
            return ResultUtil.actionSuccess("已接单，请联系学员安排课程", data);
        } else {
            return ResultUtil.connectDatabaseFail();
        }
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //瑜伽师查询“我的学生”，需要展示哪些内容？？？
     * @Param
     * @return
     **/
    @Override
    public Result listStudentByCoachId(Integer userId) {
        //联合student_coach,coach,user查找学生信息
        int coachId = coachMapper.selectCoachIdByUserId(userId);
        List<StudentVO> data = coachMapper.findStudentByUserId(userId);
        return ResultUtil.actionSuccess("查询成功", data);
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //查询所有的瑜伽流派
     * @Param
     * @return
     **/
    @Override
    public Result listCoachStyles() {
        List data = coachMapper.listCoachStyles();
        if (data.size() > 0) {
            return ResultUtil.actionSuccess("查询成功", data);
        } else {
            return ResultUtil.connectDatabaseFail();
        }
    }

    @Override
    public Result updateOrderForWaitToPay(String orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order.getOrderStatus() != OrderUtil.STARTORDER) {
            return ResultUtil.illegalOperation();
        }
        order.setOrderStatus(OrderUtil.WAITTOPAY);
        int row = orderMapper.updateStatusByOrderId(order.getOrderId(), order.getOrderStatus());
        if (row > 0) {
            return ResultUtil.actionSuccess("更新成功", order);
        }
        return ResultUtil.connectDatabaseFail();
    }

    @Override
    public Result insertCourse(int userId, Course course) {
        int coachId = coachMapper.findCoachIdByUserId(userId);
        course.setCoachId(coachId);
        int row = courseMapper.insertSelective(course);
        if (row > 0) {
            return ResultUtil.actionSuccess("新建课程成功", course);
        }
        return ResultUtil.connectDatabaseFail();
    }


    @Override
    public List<Venue> findVenueByConditions(Recruitment recruitment) {
        List venueList = coachMapper.queryVenueByConditions(recruitment);
        return venueList;
    }
}
