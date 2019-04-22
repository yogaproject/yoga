package com.woniu.yoga.user.controller;


import com.woniu.yoga.communicate.pojo.Comment;
import com.woniu.yoga.user.pojo.Order;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.service.serviceImpl.StudentServiceImpl;
import com.woniu.yoga.user.util.ResultUtil;
import com.woniu.yoga.user.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.Table;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author liufeng
 * @ClassName StudentService
 * @Date 2019/4/18 15:30
 * @Version 1.0
 * @Description 用于处理学员与后台的交互
 **/
@Controller
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;
    /*
     * @Author liufeng
     * @Date
     * @Description //根据用户注册的地址或定位地址查询附近一定范围的教练、场馆(默认查找教练)
     * @Param
     *   SearchConditionVO:搜索条件封装的类，参数如下
     *   //位置：经度
     *   private float longitude;
     *   //位置：纬度
     *   private float latitude;
     *   //距离“我”的距离，单位为米
     *   private int round;
     *   //搜索的条件：2为搜索教练，3为搜索场馆，其余不合法
     *   private int coachStyle;
     *   //教练认证方式，值为场馆或平台，其余不合法；场馆无需此项
     *   private String authenticationMethod;
     *   //教练空闲时间，值为不限、早、中、晚四选一；场馆无需此项
     *   private String freeTime;
     * @return
     **/
    public Result listAroundUserByIdOrAddress(SearchConditionVO searchConditionVO) {
        return studentService.listAroundUserByIdOrAddress(searchConditionVO);
    }
    /*
     * @Author liufeng
     * @Date
     * @Description //根据userId查询用户(瑜伽师，场馆)的详细信息
     * @Param
     *  HttpSession session:
     * @return
     *  封装了用户详细信息的数据类
     **/
    public Result getDetailInfoByUserId(HttpSession session,Integer coachId) {
        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();
        return studentService.getDetailInfoByUserId(userId,coachId);
    }
    /*
     * @Author liufeng
     * @Date
     * @Description //当用户决定下单时，对用户余额进行判定；通过则生成订单
     * @Param
     *  Order order：订单详细信息
     * @return
     *  通用返回类型
     **/
    public Result saveOrder(Order order) {
        return studentService.saveOrder(order);
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //一个订单的课程完结之后，学员准备付款(需要计算会员等级和优惠券)，返回订单确认信息
     * @Param
     *  integer orderId：订单编号
     *  integer coupon: 优惠券编号
     * @return
     *  通用返回类型，包含处理完成的订单详细信息
     **/
    public Result updateOrderWithCoupon(String orderId, Integer couponId) {
        return studentService.updateOrderWithCoupon(orderId,couponId);
    }
    /*
     * @Author liufeng
     * @Date
     * @Description //学员为订单付款，钱包金额减少，需要更新订单状态、优惠券状态等信息，并返回订单信息
     * @Param
     * @return
     **/
    public Result updateOrderForPay(String orderId) {
        return studentService.updateOrderForPay(orderId);
    }
    /*
     * @Author liufeng
     * @Date
     * @Description //学员申请退款
     * @Param
     * @return
     **/
    public Result updateOrderForRefund(String orderId){
        return studentService.updateOrderForRefund(orderId);
    }
    /*
     * @Author liufeng
     * @Date
     * @Description //保存并在前台显示评论，并对会员积分、等级等进行更新
     * @Param
     *  Comment：详细评论内容
     * @return
     *  处理完成的评论
     **/
    public Result saveComment(Comment comment) {
        return studentService.saveComment(comment);
    }
    /*
     * @Description //查询学员期望上课的所有的签约方式，因为数据比较少，因此保存在文件中；
     * @Date
     * @Param
     * @return
     *  返回签约方式的集合
     **/
    public Result listAllCourseAppoint() {
        return studentService.listAllCourseAppoint();
    }

    @RequestMapping("test")
    @ResponseBody
    public String test(){
        return ResultUtil.connectDatabaseFail().toString();
    }
}
