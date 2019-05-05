package com.woniu.yoga.user.controller;


import com.woniu.yoga.communicate.pojo.Comment;
import com.woniu.yoga.user.pojo.Order;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.service.serviceImpl.StudentServiceImpl;
import com.woniu.yoga.user.util.ResultUtil;
import com.woniu.yoga.user.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.jar.Attributes;

/**
 * @Author liufeng
 * @ClassName StudentService
 * @Date 2019/4/18 15:30
 * @Version 1.0
 * @Description 用于处理学员与后台的交互
 **/
@Controller
@RequestMapping("student")
@Api
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;





    /*
     * @Author liufeng
     * @Date
     * @Description //当用户决定下单时，对用户余额进行判定；通过则生成订单
     * @Param
     *  Order order：订单详细信息
     * @return
     *  通用返回类型
     **/
    @PostMapping("saveOrder")
    @ResponseBody
    @ApiOperation(value = "学员下单")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "order", value = "新的订单对象，无需传入用户ID", paramType = "pojo"),
            @ApiImplicitParam(name = "session", value = "HttpSession"),
    })
    public Result saveOrder(HttpSession session,@RequestBody OrderVO orderVO) {
//        User user = (User) session.getAttribute("user");
//        Integer userId = user.getUserId();
        Integer userId = 5;
        return studentService.saveOrder(userId,orderVO);
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
    @PutMapping("updateOrderWithCoupon")
    @ResponseBody
    @ApiOperation(value = "学员选择要使用的优惠券，准备付款")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "orderId", value = "订单编号", paramType = "String"),
            @ApiImplicitParam(name = "session", value = "HttpSession"),
            @ApiImplicitParam(name = "couponId",value = "优惠券的ID",required = false,paramType = "Integer")
    })
    public Result updateOrderWithCoupon(HttpSession session,@RequestBody String orderId,@RequestBody  Integer couponId) {
        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();
        return studentService.updateOrderWithCoupon(userId,orderId, couponId);
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //学员为订单付款，钱包金额减少，需要更新订单状态、优惠券状态等信息，并返回订单信息
     * @Param
     * @return
     **/
    @PutMapping("updateOrderForPay")
    @ResponseBody
    @ApiOperation(value = "学员付款")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "orderId", value = "订单编号", paramType = "String"),
            @ApiImplicitParam(name = "session", value = "HttpSession"),
    })
    public Result updateOrderForPay(HttpSession session,@RequestBody  String orderId) {
        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();
        return studentService.updateOrderForPay(userId, orderId);
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //学员申请退款
     * @Param
     * @return
     **/
    @PutMapping("updateOrderForRefund")
    @ResponseBody
    @ApiOperation(value = "学员申请退款")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "orderId", value = "订单编号", paramType = "String"),
            @ApiImplicitParam(name = "session", value = "HttpSession"),
    })
    public Result updateOrderForRefund(HttpSession session,@RequestBody  String orderId) {
        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();
        return studentService.updateOrderForRefund(userId, orderId);
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
    @PostMapping("saveComment")
    @ResponseBody
    @ApiOperation(value = "学员发表订单评论")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "orderId", value = "订单编号", paramType = "String"),
            @ApiImplicitParam(name = "session", value = "HttpSession"),
            @ApiImplicitParam(name = "comment", value = "一个新的评论对象")
    })
    public Result saveComment(HttpSession session,@RequestBody  String orderId,@RequestBody  Comment comment) {
        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();
        return studentService.saveComment(userId, orderId, comment);
    }

    /*
     * @Description //查询学员期望上课的所有的签约方式，因为数据比较少，因此保存在文件中；
     * @Date
     * @Param
     * @return
     *  返回签约方式的集合
     **/
    @GetMapping("listAllCourseAppoint")
    @ResponseBody
    @ApiOperation(value = "学员查看所有的签约（下单）方式")
    public Result listAllCourseAppoint() {
        return studentService.listAllCourseAppoint();
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //处理用户取消订单的请求
     * @Param
     * @return
     **/
    @PostMapping("updateOrderForCancel")
    @ResponseBody
    @ApiOperation(value = "学员取消订单")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "orderId", value = "订单编号", paramType = "String"),
            @ApiImplicitParam(name = "session", value = "HttpSession")
    })
    public Result updateOrderForCancel(HttpSession session,@RequestBody  String orderId) {
        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();
        return studentService.updateOrderForCancel(userId, orderId);
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //处理用户线下签约的请求，查询瑜伽师的电话号码
     * @Param
     * @return
     **/
    @PostMapping("findCoachPhoneByUserId")
    @ResponseBody
    @ApiOperation(value = "学员申请线下签约，查询对应瑜伽师的电话号码")
    @ApiImplicitParam(name = "userId", value = "瑜伽师的用户ID")
    public Result findCoachPhoneByUserId(@RequestBody Integer userId) {
        return studentService.findCoachPhoneByUserId(userId);
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //处理学员重复下单的请求
     * @Param
     * @return
     **/
    @PostMapping("repeatOrder")
    @ResponseBody
    @ApiOperation(value = "学员重复下单")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "session", value = "HttpSession"),
            @ApiImplicitParam(name = "orderId", value = "旧的订单ID", paramType = "String")
    })
    public Result repeatOrder(HttpSession session,@RequestBody  String orderId) {
        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();
        return studentService.repeatOrder(userId, orderId);
    }

    @RequestMapping("test")
    @ResponseBody
    public String test() {
        return ResultUtil.connectDatabaseFail().toString();
    }
}
