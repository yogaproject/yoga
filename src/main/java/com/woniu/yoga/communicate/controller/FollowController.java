package com.woniu.yoga.communicate.controller;

import com.woniu.yoga.commom.utils.JsonUtil;
import com.woniu.yoga.communicate.constant.SysConstant;
import com.woniu.yoga.communicate.service.FollowService;
import com.woniu.yoga.communicate.vo.FollowVo;
import com.woniu.yoga.communicate.vo.InformationVo;
import com.woniu.yoga.home.vo.HomepageVo;
import com.woniu.yoga.home.vo.Result;
import com.woniu.yoga.user.pojo.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author huijie yan
 * @version 1.0.0
 * @description 我的关注
 * @date 2019/4/24 12:08
 */
@RestController
@RequestMapping("/follow")
public class FollowController {


    @Autowired
    private FollowService followService;

    /**
    * @Description 查看我的关注人列表或我的粉丝 传入0为查找我的粉丝,传入1位查找我的关注人列表
    * @param vo
    * @author huijie yan
    * @date 2019/5/6
    * @return com.woniu.yoga.home.vo.Result<java.util.List<com.woniu.yoga.communicate.vo.FollowVo>>
    */
    @ApiOperation(value = "查看我的关注人列表或我的粉丝")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "state", value = "传入0查找我的粉丝,传入1查找我的关注人列表", required = true, paramType = "path"),
    })
    @PostMapping("/showFollowList")
    public Result<List<FollowVo>> showFollowList(@RequestBody InformationVo vo){
        return followService.showFollowList(vo.getState(), Integer.parseInt(vo.getUserId()));
    }

    /**
    * @Description 查看我的关注人发表的动态
    * @param
    * @author huijie yan
    * @date 2019/4/24
    * @return com.woniu.yoga.commom.vo.Result
    */
    @ApiOperation(value = "查看我的关注人发表的动态")
    @PostMapping("/showFollowHomepage")
    @ResponseBody
    public Result<List<HomepageVo>> showFollowHomepage(HttpSession session){
        return followService.showFollowHomepage(session);
    }

    /**
    * @Description 关注用户
    * @param userId
    * @author huijie yan
    * @date 2019/4/24
    * @return com.woniu.yoga.commom.vo.Result
    */
    @ApiOperation(value = "关注用户")
    @ApiImplicitParam(name = "userId", value = "要关注的用户id", required = true, paramType = "path")
    @PostMapping("/addFollow")
    public Result addFollow(@RequestBody Integer userId, HttpSession session){
        return followService.addFollow(userId, session);
    }

    /**
    * @Description 取消关注
    * @param userId
    * @param session
    * @author huijie yan
    * @date 2019/4/25
    * @return com.woniu.yoga.commom.vo.Result
    */
    @ApiOperation(value = "对用户取关")
    @ApiImplicitParam(name = "userId", value = "要取关的用户id", required = true, paramType = "path")
    @PostMapping("/cancelFollow")
    public Result cancelFollow(@RequestBody Integer userId, HttpSession session){
        return followService.cancelFollow(userId, session);
    }

    /**
    * @Description 模糊查询关注的人
    * @param userNickName
    * @param session
    * @author huijie yan
    * @date 2019/4/29
    * @return java.lang.String
    */
    @ApiOperation(value = "模糊查询关注的人")
    @ApiImplicitParam(name = "userNickName", value = "要查询的名字", required = true, paramType = "path")
    @PostMapping("/searchFollow")
    public Result<FollowVo> searchFollow(@RequestBody String userNickName, HttpSession session){
        User user = (User)session.getAttribute(SysConstant.CURRENT_USER);
        if (user == null){
            return  Result.error("未登录");
        }
        return followService.searchFollow(userNickName, user.getUserId());
    }

    /**
    * @Description 查找我的主页信息
    * @param session
    * @author huijie yan
    * @date 2019/5/5
    * @return com.woniu.yoga.home.vo.Result
    */
    @ApiOperation(value = "查找我的主页信息")
    @PostMapping("/showMyself")
    public Result showMyself(HttpSession session){
        User user = (User)session.getAttribute(SysConstant.CURRENT_USER);
        return  followService.showMyself(user.getUserId());
    }
}
