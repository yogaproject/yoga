package com.woniu.yoga.user.controller;


import com.woniu.yoga.commom.aspect.ControllerAOP;
import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.user.constant.SysConstant;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.service.UserService;
import com.woniu.yoga.user.vo.SearchConditionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


/**
 * @Author liufeng
 * @ClassName StudentService
 * @Date 2019/4/18 15:30
 * @Version 1.0
 * @Description 用于处理用户与后台的交互
 **/
@Controller
@RequestMapping("user")
@Api
public class UserController {
    @Autowired
    private UserService userService;

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
     *   private String authentication;
     *   //教练空闲时间，值为不限、早、中、晚四选一；场馆无需此项
     *   private String freeTime;
     * @return
     **/
    @PostMapping("listAroundCoachByAddress")
    @ResponseBody
    @ApiOperation(value = "学员查看附近的瑜伽师")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "searchConditionVO", value = "多个搜索条件封装成的对象，包含以下属性"),
            @ApiImplicitParam(name = "realName", value = "要查询的瑜伽师的名称"),
            @ApiImplicitParam(name = "longitude", value = "学员位置：经度（百度地图）", paramType = "double", required = true),
            @ApiImplicitParam(name = "latitude", value = "学员位置：纬度（百度地图）", paramType = "double", required = true),
            @ApiImplicitParam(name = "round", value = "距离‘我的距离’", defaultValue = "2000"),
            @ApiImplicitParam(name = "roleId", value = "2:瑜伽师；3：场馆；其余默认为瑜伽师", defaultValue = "2", paramType = "integer"),
            @ApiImplicitParam(name = "coachStyle", value = "瑜伽师流派，从数据库中查询，0为不限;场馆无需此项", required = false, defaultValue = "0", paramType = "integer"),
            @ApiImplicitParam(name = "authentication", value = "瑜伽师认证方式：不限、平台认证或场馆认证;场馆无需此项", required = false, defaultValue = "不限", paramType = "String"),
            @ApiImplicitParam(name = "freeTime", value = "瑜伽师自定义的空闲时间，值为不限、早、中、晚、全天", required = false, defaultValue = "不限", paramType = "String")
    })
    public Result listAroundCoachByAddress(@RequestBody SearchConditionVO searchConditionVO) {

        // System.out.println(searchConditionVO);
        Result result = userService.listAroundCoachs(searchConditionVO);
        // System.out.println(result);
        return result;
    }

    /*
     * @Author liufeng
     * @Description //根据用户注册的地址或定位地址查询附近一定范围的教练、场馆(默认查找教练)
     **/
    @PostMapping("listAroundVenueByAddress")
    @ResponseBody
    @ApiOperation(value = "学员查看附近的场馆")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "realName", value = "要查询的场馆的名称"),
            @ApiImplicitParam(name = "searchConditionVO", value = "多个搜索条件封装成的对象，包含以下属性"),
            @ApiImplicitParam(name = "longitude", value = "学员位置：经度（百度地图）", paramType = "double", required = true),
            @ApiImplicitParam(name = "latitude", value = "学员位置：纬度（百度地图）", paramType = "double", required = true),
            @ApiImplicitParam(name = "round", value = "距离‘我的距离’", defaultValue = "2000"),
    })
    public Result listAroundVenueByAddress(@RequestBody SearchConditionVO searchConditionVO) {
        //System.out.println(searchConditionVO);
        Result result = userService.listAroundVenues(searchConditionVO);
        //System.out.println(result);
        return result;
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //根据订单状态，查询用户订单
     * @Param
     *  String orderStatus：订单状态，默认为“所有订单”，只能为“未完成订单”，“已完成”，“所有订单”，其余为非法
     * @return
     *  返回查询到的订单结果集合，参数不合法返回所有
     **/
    @PostMapping("listOrder")
    @ResponseBody
    @ApiOperation(value = "查看我的订单")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "session", value = "HttpSession"),
            @ApiImplicitParam(name = "orderStatus", value = "订单状态：未完成订单、已完成订单、所有订单")
    })
    public Result listOrder(HttpSession session, @RequestBody String orderStatus) {
        User user = (User) session.getAttribute(SysConstant.CURRENT_USER);
        Integer userId = user.getUserId();
        //Integer userId = 4;//教练
        return userService.listOrder(userId, orderStatus);
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //根据用户Id查找可用的优惠券
     * @Param
     *  Integer userId：用户id
     * @return
     *  返回可用优惠券的集合
     **/
    @ApiOperation(value = "查找用户有效的优惠券")
    @ApiImplicitParam(name = "session", value = "HttpSession")
    @RequestMapping("listCouponsByUserId")
    @ResponseBody
    public Result listCouponsByUserId(HttpSession session) {
        User user = (User) session.getAttribute(SysConstant.CURRENT_USER);
        Integer userId = user.getUserId();
        //Integer userId = 5;//学员
        return userService.listCouponsByUserId(userId);
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
    @PostMapping("getDetailInfoByUserId")
    @ResponseBody
    @ApiOperation(value = "学员查看瑜伽师信息，非好友私人信息为null")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "session", value = "HttpSession"),
            @ApiImplicitParam(name = "coachId", value = "瑜伽师的用户ID", paramType = "integer"),
    })
    public Result getDetailInfoByUserId(HttpSession session, @RequestBody(required = false) Integer coachId) {
        Integer userId = null;
        if (coachId == null) {
            User user = (User) session.getAttribute(SysConstant.CURRENT_USER);
            userId = user.getUserId();
            coachId = userId;
        }
//        Integer userId = 2;
        return userService.getDetailInfoByUserId(userId, coachId);
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //查询场馆的详细信息
     * @Param
     * @return
     **/
    @ApiOperation(value = "查看场馆详细信息")
    @ApiImplicitParam(name = "venueId", value = "场馆的用户Id")
    @RequestMapping("getVenueDetailInfoByUserId")
    @ResponseBody
    public Result getVenueDetailInfoByUserId(HttpSession session, @RequestBody(required = false) Integer venueId) {
        if (venueId == null) {
            User user = (User) session.getAttribute(SysConstant.CURRENT_USER);
            Integer userId = user.getUserId();
            venueId = userId;
        }
        return userService.getVenueDetailInfoByUserId(venueId);
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //查看我发表的动态
     * @Param
     * @return
     **/
    @RequestMapping("getAllMyInfos")
    @ResponseBody
    public Result getAllMyInfos(HttpSession session) {
        //        Integer userId = null;
        //        try {
        User user = (User) session.getAttribute(SysConstant.CURRENT_USER);
        Integer userId = user.getUserId();
        //        } catch (Exception e) {
        //            userId = 1;
        //        }
        return userService.getAllMyInfos(userId);
    }

    @ApiOperation(value = "瑜伽室查看我的课程的评论")
    @RequestMapping("getAllMyComments")
    @ResponseBody
    public Result getAllMyComments(HttpSession session) {
//        Integer userId = null;
//        try {
        User user = (User) session.getAttribute(SysConstant.CURRENT_USER);
        Integer userId = user.getUserId();
//        } catch (Exception e) {
//            userId = 4;
//        }
        return userService.getAllMyComments(userId);
    }

    @ApiOperation(value = "用户查看我关注的用户")
    @RequestMapping("getAllMyFocus")
    @ResponseBody
    public Result getAllMyFocus(HttpSession session) {
        //        Integer userId = null;
        //        try {
        User user = (User) session.getAttribute(SysConstant.CURRENT_USER);
        Integer userId = user.getUserId();
        //        } catch (Exception e) {
        //            userId = 1;
        //        }
        return userService.getAllMyFocus(userId);
    }

    @ApiOperation(value = "用户查看我的粉丝")
    @RequestMapping("getAllMyFans")
    @ResponseBody
    public Result getAllMyFans(HttpSession session) {
        //        Integer userId = null;
        //        try {
        User user = (User) session.getAttribute(SysConstant.CURRENT_USER);
        Integer userId = user.getUserId();
        //        } catch (Exception e) {
        //            userId = 1;
        //        }
        return userService.getAllMyFans(userId);
    }

    @ApiOperation(value = "")
    @RequestMapping("getStudentInfo")
    @ResponseBody
    public Result getStudentInfo(HttpSession session, @RequestBody(required = false) Integer otherId) {
        if (otherId == null) {
            User user = (User) session.getAttribute(SysConstant.CURRENT_USER);
//            System.out.println("user controller user="+user);
            Integer userId = user.getUserId();
            otherId = userId;
        }
        return userService.getStudentInfo(otherId);
    }

}
