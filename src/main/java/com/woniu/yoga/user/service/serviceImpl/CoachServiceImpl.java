package com.woniu.yoga.user.service.serviceImpl;

import com.woniu.yoga.commom.utils.Attributes;
import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.user.dao.CoachMapper;
import com.woniu.yoga.user.dao.CourseMapper;
import com.woniu.yoga.user.dao.OrderMapper;
import com.woniu.yoga.user.pojo.Coach;
import com.woniu.yoga.user.pojo.Course;
import com.woniu.yoga.user.pojo.Order;
import com.woniu.yoga.user.repository.CoachRepository;
import com.woniu.yoga.user.service.CoachService;
import com.woniu.yoga.user.util.OrderUtil;
import com.woniu.yoga.user.util.ResultUtil;
import com.woniu.yoga.user.vo.StudentVO;
import com.woniu.yoga.venue.pojo.Recruitment;
import com.woniu.yoga.venue.pojo.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {
    @Autowired
    private CoachMapper coachMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CoachRepository coachRepository;

    @Override
    public void saveCoach(Coach coach) {
        coachRepository.save(coach);
    }

    @Override
    public Coach findCoachByUserId(Integer userId) {
        return coachRepository.findCoachByUserId(userId);
    }
    @Override
    public Coach findCoachByCoachId(Integer coachId) {
        try {
            Coach coach = coachMapper.selectByPrimaryKey(coachId);
            return coach;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
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
        try {
            //根据操作更改订单状态
            Order order = orderMapper.selectByPrimaryKey(orderId);
            if (order.getOrderStatus() != OrderUtil.NEWORDER) {
                ResultUtil.illegalOperation();
            }
            int orderStatus = 0;
            if (result.equals("accept")) {
                orderStatus = OrderUtil.STARTORDER;
            } else if (result.equals("cancel")) {
                orderStatus = OrderUtil.CANCELED;
            } else {
                ResultUtil.illegalOperation();
            }
            order.setOrderStatus(orderStatus);
            orderMapper.updateStatusByOrderId(orderId,orderStatus);
            return ResultUtil.actionSuccess("已接单，请联系学员安排课程", order);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
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
        try {
            int coachId = coachMapper.selectCoachIdByUserId(userId);
            List<StudentVO> studentVOS = coachMapper.findStudentByUserId(userId);
            return ResultUtil.actionSuccess("查询成功", studentVOS);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
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
        try {
            List data = coachMapper.listCoachStyles();
            return ResultUtil.actionSuccess("查询成功", data);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Result updateOrderForWaitToPay(Integer userId, String orderId) {
        try {
            Order order = orderMapper.selectByPrimaryKey(orderId);
            if (order.getAccepterId() != userId) {
                return ResultUtil.illegalOperation();
            }
            if (order.getOrderStatus() != OrderUtil.STARTORDER) {
                return ResultUtil.illegalOperation();
            }
            order.setOrderStatus(OrderUtil.WAITTOPAY);
            orderMapper.updateStatusByOrderId(order.getOrderId(), order.getOrderStatus());
            return ResultUtil.actionSuccess("更新成功", order);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Result insertCourse(int userId, Course course) {
        try {
            Integer coachId = coachMapper.selectCoachIdByUserId(userId);
            Integer courseIdOld = courseMapper.selectByCoachIdAndCOurseName(coachId, course.getCourseName());
            if (courseIdOld != null) {
                return ResultUtil.errorOperation("该课程已存在，请到课程页面查看，或者更改课程名");
            }
            course.setCoachId(coachId);
            courseMapper.insertSelective(course);
            return ResultUtil.actionSuccess("新建课程成功", course);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Result applyForSign(int userId, Integer venueId) {
        try {
            Integer coachId = coachMapper.selectCoachIdByUserId(userId);
            Integer authentication = coachMapper.selectAuthenticationByCoachId(coachId);
            if (authentication != 0) {
                return ResultUtil.errorOperation("已签约，不能重复申请");
            }
            coachMapper.applyForSign(coachId, venueId);
            return ResultUtil.actionSuccess("已发出申请，等待场馆处理中...", null);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Result dealVenueRequest(int userId, Integer venueId, String result) {
        try {
            Integer coachId = coachMapper.selectCoachIdByUserId(userId);
            Integer authentication = coachMapper.selectAuthenticationByCoachId(coachId);
            if (authentication != 0) {
                return ResultUtil.errorOperation("出错啦，请与管理员联系!");
            }
            Integer cvId = coachMapper.selectSignWithCoachIdAndVenueId(coachId, venueId);
            if (cvId == null) {
                return ResultUtil.errorOperation("请求错误，请联系管理员！");
            }
            if (result.equals("接受")) {
                authentication = 1;
                if (venueId == Attributes.PLATFORMNUMBER) {
                    authentication = 2;
                }
                coachMapper.updateCoachVenueStatus(coachId, 1);
                coachMapper.updateAuthenticationForSign(coachId, authentication);
            } else if (result.equals("拒绝")) {
                coachMapper.updateCoachVenueStatus(coachId, 2);
            } else {
                return ResultUtil.errorOperation("出错啦，请与管理员联系!");
            }
            //给场馆发送一条信息
            return ResultUtil.actionSuccess("处理成功", null);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Result cancelContract(int userId) {
        try {
            Integer coachId = coachMapper.selectCoachIdByUserId(userId);
            coachMapper.updateCoachVenueStatus(coachId, 2);
            coachMapper.updateAuthenticationForSign(coachId, 0);
            return ResultUtil.actionSuccess("解约成功", null);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Result deleteCourse(int userId, Integer courseId) {
        try {
            int row = courseMapper.deleteCourse();
            return ResultUtil.actionSuccess("删除成功", null);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Result updateCourse(int userId, Course course) {
        try {
            Integer coachId = coachMapper.selectCoachIdByUserId(userId);
            course.setCoachId(coachId);
            courseMapper.updateByPrimaryKeySelective(course);
            return ResultUtil.actionSuccess("修改成功", course);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    @Override
    public List<Venue> findVenueByConditions(Recruitment recruitment) {
        try {
            List venueList = coachMapper.queryVenueByConditions(recruitment);
            return venueList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
