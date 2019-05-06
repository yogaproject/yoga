package com.woniu.yoga.venue.controller;

import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.venue.pojo.Coach;
import com.woniu.yoga.user.pojo.Course;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.venue.pojo.Recruitment;
import com.woniu.yoga.venue.pojo.Venue;
import com.woniu.yoga.venue.service.VenueService;
import com.woniu.yoga.venue.vo.CoachInformationVO;
import com.woniu.yoga.venue.vo.VenueInformationVO;
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


    //根据userId查询venueId
    @RequestMapping("/getVenueId")
    @ResponseBody
    public int getVenueId( Integer userId){
        return venueService.getVenueIdByUserId(userId);
    }


    //---------------------------------------------信息完善-----------------------------------------
    //参数：Venue ，1.根据场馆id，添加图片、场馆详情 —》
    //              2.根据venue里面的userId，在user表添加场馆地址、场馆名称、场馆电话、qq
    //
    @RequestMapping("/venuePerfectInformation")
    @ResponseBody
    public Result venuePerfectInformation(Venue venue, User user,Course course){
        System.out.println("信息完善controller1");
        int num1 = venueService.venuePerfectInformationService(venue);
        if (num1 == 0){
            //场馆信息添加失败
            return Result.error("场馆信息添加失败");
        }
        System.out.println("信息完善controller2");
        //场馆信息添加成功，根据userId，user表添加内容
        //根据userId查venueId
        int uid = venueService.selectUserIdByVenueId(venue.getVenueId());
        System.out.println(uid);
        int num2 = venueService.addVenueUserInformationService(uid,user);
          if (num2 == 0){
              return Result.error("用户信息添加失败");
          }
        System.out.println("controller2执行完了");
            venueAllCoach(venue);//调用此方法，展示场馆所有教练
        //根据多个文本款内容，传回来一个course对象，在课程表插入数据，代表场馆给教练安排课程
           // venueService.coachAddCourseService(course);
        return Result.success("场馆添加信息成功");
    }
    //展示出场馆信息完善以后，下拉框自动调用此方法
    //3.根据场馆id，在option下拉框中显示此场馆已经签约的教练，并在文本框数据自定义课程内容，在课程表插入
    @RequestMapping("/venueAllCoach")
    @ResponseBody
    public List<Coach> venueAllCoach(Venue venue){
        List coachList = venueService.findCoachByVenueIdService(venue.getVenueId());
        return coachList;
    }







    //---------------------------------------------教练查看-----------------------------------------
    //场馆根据选择（教练类型、期望薪资、流派），查询符合条件的教练集合
    @RequestMapping("/vagueConditions")
    @ResponseBody
    public List<Coach> selectCoachByVagueConditions(Coach coach, BigDecimal upExpectedSalary,BigDecimal downExpectedSalary){
        List<Coach> listCoach = venueService.findCoachByVagueConditions(coach,upExpectedSalary,downExpectedSalary);
        System.out.println(listCoach);
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

    //查询下拉框使用的教练类型和流派
    @RequestMapping("/coachStyle")
    @ResponseBody
    public List<Coach> coachStyle(){
        List<Coach> coachStyle =venueService.coachStyle();
        return coachStyle;
    }

    @RequestMapping("/coachType")
    @ResponseBody
    public List<Coach> coachType(){
        List<Coach> coachType = venueService.coachType();
       return coachType;
    }



// -------------------------------------------签约------------------------------------------------
    //签约教练--场馆找到教练后，可以选择发起请求，申请“场馆教练”。
    //必须要教练同意，确认后，即可成为该场馆的签约教练

    //场馆选中教练，点击申请签约的按钮,中间表生成“等待用户同意”的数据
    @RequestMapping("/applyForSign")
    @ResponseBody
    public Result applyForSign(Integer venueId,Integer coachId){
        int num = venueService.waitCoachForSign(venueId,coachId);
        if (num==0){
            return Result.error("申请失败");
        }
        return Result.success("申请成功");
    }

 /*   //教练看到场馆的申请消息，签约，其状态修改为“1”“签约”
    @RequestMapping("/coachSign")
    @ResponseBody
    public Result coachSign(Integer cv_id){
        int num = venueService.coachSignService(cv_id);
        if (num == 0){
            return Result.error("签约失败");
        }
        return Result.success("签约成功");
    }

    //教练看到场馆的申请消息，拒绝，将其签约状态改为“2”“解约”
    @RequestMapping("/coachRefuse")
    @ResponseBody
    public Result coachRefuse(Integer cv_id){
        int num = venueService.coachRefuseService(cv_id);
        if (num == 0){
            return Result.error("拒绝失败");
        }
        return Result.success("拒绝成功");
    }*/

    //--------------------------------------招聘----------------------------------------
    //场馆发布招聘信息，在招聘表里添加数据
    @RequestMapping("/venueAddRecruit")
    @ResponseBody
    public Result venueAddRecruit(Recruitment recruitment){
        int num = venueService.venueAddRecruitService(recruitment);
        if (num == 0){
           return Result.error("发布招聘失败");
        }
        return Result.success("发布招聘成功");
    }
    //---------------------------------------我的签约教练------------------------------------
    //场馆可以查询和他签约的教练，并且可以和教练解约
    @RequestMapping("/venueSelectCoach")
    @ResponseBody
    public  List<CoachInformationVO> venueSelectCoach(CoachInformationVO coachInformationVO){

        List coachInformationList = venueService.venueFindCoach(coachInformationVO);
        System.out.println(coachInformationList);
        return coachInformationList;
    }

    //场馆选择教练id（参数coachId），把选中教练‘解约’（venue_coach表coachId修改签约状态）
   @RequestMapping("/venueBreakCoach")
   @ResponseBody
    public Result venueBreakCoach(Integer coachId){
        int num = venueService.venueBreakCoachService(coachId);
        if (num == 0){
           return Result.error("解约失败");
        }
        return Result.success("解约成功");
    }

    //根据场馆id，查询场馆VO类信息
    @RequestMapping("/selectVenueVOByVenueId")
    @ResponseBody
    public VenueInformationVO selectVenueVOByVenueId(Integer venueId){
        VenueInformationVO venueInformationVO = venueService.selectVenueVOByVenueId(venueId);
        return venueInformationVO;
}










}
