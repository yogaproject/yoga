package com.woniu.yoga.venue.controller;

import com.woniu.yoga.user.pojo.Coach;
import com.woniu.yoga.venue.pojo.Recruitment;
import com.woniu.yoga.venue.pojo.Venue;
import com.woniu.yoga.venue.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/venue")
public class VenueController {
    @Autowired
    private VenueService venueService;

    String result;

    //场馆根据选择（教练类型、期望薪资、流派）的id，查询符合条件的教练集合
    @RequestMapping("/vagueConditions")
    @ResponseBody
    public List<Coach> selectCoachByVagueConditions(Coach coach, BigDecimal up_expected_salary,BigDecimal down_expected_salary){
        List<Coach> listCoach = venueService.findCoachByVagueConditions(coach,up_expected_salary,down_expected_salary);
        return listCoach;
    }

    //根据场馆id查询场馆信息（用户点击教练签约的场馆，出现场馆信息）
    //用户点每点击一次，调用这个方法，场馆的访问量要+1------------------未完成
    @RequestMapping("/venueInformation")
    @ResponseBody
    public Venue selectVenueByVenueId(Integer venueId){
        Venue venue = venueService.findVenueByVenueId(venueId);
        return venue;
    }
// -------------------------------------------签约------------------------------------------------
    //签约教练--场馆找到教练后，在教练的详情页面中，可以选择发起请求，申请“场馆教练”。
    //必须要教练同意，确认后，即可成为该场馆的签约教练

    //场馆选中教练，点击申请签约的按钮,中间表生成“等待用户同意”的数据
    @RequestMapping("/applyForSign")
    @ResponseBody
    public String applyForSign(Integer venueId,Integer coachId){
        int num = venueService.waitCoahForSign(venueId,coachId);
        if (num==0){
            result = "申请失败";
            return result;
        }
        result = "申请成功";
        return result;
    }

    //教练看到场馆的申请消息，签约，将其状态修改为“1”“签约”
    @RequestMapping("/coachSign")
    @ResponseBody
    public String coachSign(Integer cv_id){
        int num = venueService.coachSignService(cv_id);
        if (num == 0){
            result = "签约失败";
        }
        result = "签约成功";
        return result;
    }

    //教练看到场馆的申请消息，拒绝，将其签约状态改为“2”“解约”
    @RequestMapping("/coachRefuse")
    @ResponseBody
    public String coachRefuse(Integer cv_id){
        int num = venueService.coachRefuseService(cv_id);
        if (num == 0){
            result = "拒绝失败";
        }
        result = "拒绝成功";
        return result;
    }

    //--------------------------------------招聘----------------------------------------
    //场馆发布招聘信息，在招聘表里添加数据
    @RequestMapping("/venueAddRecruit")
    @ResponseBody
    public String venueAddRecruit(Recruitment recruitment){
        int num = venueService.venueAddRecruitService(recruitment);
        if (num == 0){
            result = "发布招聘失败";
        }
        result = "发布招聘成功";
        return result;
    }










}
