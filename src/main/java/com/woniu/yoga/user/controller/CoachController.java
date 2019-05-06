package com.woniu.yoga.user.controller;

import com.woniu.yoga.user.pojo.Coach;
import com.woniu.yoga.user.pojo.Course;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.service.CoachService;
import com.woniu.yoga.user.vo.Result;
import com.woniu.yoga.venue.pojo.Recruitment;
import com.woniu.yoga.venue.pojo.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/coach")
public class CoachController {
    @Autowired
    private CoachService coachService;

    //---------------------------------------------------------------------------




    //-------------------------------------------------------------------------

    //根据教练id查询教练的详细信息（包含了签约教练和没有签约教练，如果场馆id字段不为空，就是签约教练就把教练
    // 和场馆的签约信息也展示出来，否则，不展示此信息）
    @RequestMapping("/coachInformation")
    @ResponseBody
    public Coach selectCoachByCoachId(Integer coachId){
        Coach coach = coachService.findCoachByCoachId(coachId);
        return coach;
    }

    //-------------------------------------招聘------------------------------------------
    //模糊查询场馆list(是否需要招聘(需要场馆已经发布了招聘信息，从招聘表中去查)，招聘流派，工资待遇)
   @RequestMapping("/selectVenueByConditions")
   @ResponseBody
    public List<Venue> selectVenueByConditions(Recruitment recruitment){
        List venueList = coachService.findVenueByConditions(recruitment);
        return  venueList;
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
    public Result updateOrder(String orderId, String result) {
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
    @RequestMapping("listStudentByCoachId")
    @ResponseBody
    public Result listStudentByCoachId(HttpSession session) {
//        User user = (User) session.getAttribute("user");
//        int userId = user.getUserId();
        int userId = 1;
        return coachService.listStudentByCoachId(userId);
    }
    /*
     * @Author liufeng
     * @Date
     * @Description //课程完结之后，教练更改订单状态为待付款
     * @Param
     * @return
     **/
    public Result updateOrderForWaitToPay(String orderId){
        return coachService.updateOrderForWaitToPay(orderId);
    }
    /*
     * @Author liufeng
     * @Date
     * @Description //查找所有的流派（教练）
     * @Param
     * @return
     **/
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
    @RequestMapping("insertCourse")
    @ResponseBody
    public Result insertCourse(HttpSession session,Course course){
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        return coachService.insertCourse(userId,course);
    }



}
