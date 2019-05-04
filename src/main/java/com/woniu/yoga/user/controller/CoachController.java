package com.woniu.yoga.user.controller;

import com.woniu.yoga.user.pojo.Coach;
import com.woniu.yoga.user.pojo.Course;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.service.CoachService;
import com.woniu.yoga.user.vo.Result;
import com.woniu.yoga.venue.pojo.Recruitment;
import com.woniu.yoga.venue.pojo.Venue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/coach")
@Api(value = "CoachController|瑜伽师订单/课程处理")
public class CoachController {
    @Autowired
    private CoachService coachService;

    //根据教练id查询教练的详细信息（包含了签约教练和没有签约教练，如果场馆id字段不为空，就是签约教练就把教练
    // 和场馆的签约信息也展示出来，否则，不展示此信息）
    @RequestMapping("/coachInformation")
    @ResponseBody
    public Coach selectCoachByCoachId(@RequestBody Integer coachId) {
        Coach coach = coachService.findCoachByCoachId(coachId);
        return coach;
    }

    //-------------------------------------招聘------------------------------------------
    //模糊查询场馆list(是否需要招聘(需要场馆已经发布了招聘信息，从招聘表中去查)，招聘流派，工资待遇)
    @RequestMapping("/selectVenueByConditions")
    @ResponseBody
    public List<Venue> selectVenueByConditions(@RequestBody Recruitment recruitment) {
        List venueList = coachService.findVenueByConditions(recruitment);
        return venueList;
    }


    /*
     * @Author liufeng
     * @Date
     * @Description //教练对未处理订单进行处理，是否接受订单
     * @Param
     *  Integer orderId：订单编号
     *  String result：是否接受约教；只能传入“接受”或“拒绝”
     * @return
     *  通用返回类型，附带订单更新后的详细信息
     **/
    @PostMapping("/updateOrderForNewOrder")
    @ResponseBody
    @ApiOperation(value = "瑜伽师对学员新下订单进行处理")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "orderId", value = "订单ID",paramType = "String"),
            @ApiImplicitParam(name = "result", value = "接受或拒绝",paramType = "String"),
    })
    public Result updateOrderForNewOrder(@RequestBody String orderId, @RequestBody String result) {
        return coachService.updateOrder(orderId, result);
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //查询该瑜伽师的学员
     * @Param
     * @return
     *  返回查询到的学员的结果集
     **/
    @GetMapping("listStudentByCoachId")
    @ResponseBody
    @ApiOperation(value = "瑜伽师查看我的学员")
    public Result listStudentByCoachId(HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
//        int userId = 1;
        return coachService.listStudentByCoachId(userId);
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //课程完结之后，教练更改订单状态为待付款
     * @Param
     * @return
     **/
    @PostMapping("/updateOrderForWaiToPay")
    @ResponseBody
    @ApiOperation(value = "订单课程完成之后，瑜伽师修改订单状态为待付款")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "orderId", value = "订单编号",paramType = "String"),
            @ApiImplicitParam(name = "session", value = "HttpSession")
    })
    public Result updateOrderForWaitToPay(@RequestBody String orderId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        return coachService.updateOrderForWaitToPay(userId, orderId);
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //查找所有的流派（教练）
     * @Param
     * @return
     **/
    @GetMapping("/listCoachStyles")
    @ResponseBody
    @ApiOperation(value = "查找所有的瑜伽师流派")
    public Result listCoachStyles() {
        return coachService.listCoachStyles();
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //瑜伽师创建课程
     * @Param
     * @return
     **/
    @PostMapping("insertCourse")
    @ResponseBody
    @ApiOperation(value = "瑜伽师添加新的课程")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "session", value = "HttpSession"),
            @ApiImplicitParam(name = "course", value = "一个课程对象",paramType = "pojo")
    })
    public Result insertCourse(HttpSession session,@RequestBody Course course) {
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        return coachService.insertCourse(userId, course);
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //删除课程
     * @Param
     * @return
     **/
    @DeleteMapping("deleteCourse")
    @ResponseBody
    @ApiOperation(value = "瑜伽师删除课程")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "session", value = "HttpSession"),
            @ApiImplicitParam(name = "courseId", value = "课ID",paramType = "Integer")
    })
    public Result deleteCourse(HttpSession session,@RequestBody  Integer courseId) {
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        return coachService.deleteCourse(userId, courseId);
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //修改课程
     * @Param
     * @return
     **/
    @PostMapping("updateCourse")
    @ResponseBody
    @ApiOperation(value = "瑜伽师更新课程")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "session", value = "HttpSession"),
            @ApiImplicitParam(name = "course", value = "修改后的课程对象")
    })
    public Result updateCourse(HttpSession session,@RequestBody  Course course) {
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        return coachService.updateCourse(userId, course);
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //瑜伽师申请与场馆（平台）签约
     * @Param
     * @return
     **/
    @PostMapping("applyForSign")
    @ResponseBody
    @ApiOperation(value = "瑜伽师申请认证")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "session", value = "HttpSession"),
            @ApiImplicitParam(name = "venueId", value = "场馆id，或者平台id")
    })
    public Result applyForSign(HttpSession session,@RequestBody  Integer venueId) {
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        return coachService.applyForSign(userId, venueId);
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //瑜伽师处理场馆申请签约的请求
     * @Param
     *  result:"接受","拒绝"
     * @return
     **/
    @PostMapping("dealVenueRequestSign")
    @ResponseBody
    @ApiOperation(value = "瑜伽师处理场馆（平台）发出的认证请求")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "session",value = "HttpSession"),
            @ApiImplicitParam(name = "venueId",value = "场馆（平台）Id",paramType = "Integer"),
            @ApiImplicitParam(name = "result",value = "接受或拒绝",paramType = "String"),
    })
    public Result dealVenueRequestSign(HttpSession session,@RequestBody Integer venueId,@RequestBody  String result) {
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        return coachService.dealVenueRequest(userId, venueId, result);
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //瑜伽师申请解约
     * @Param
     * @return
     **/
    @DeleteMapping("cancelContract")
    @ResponseBody
    @ApiOperation(value = "瑜伽师借阅")
    public Result cancelContract(HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        return coachService.cancelContract(userId);
    }


}
