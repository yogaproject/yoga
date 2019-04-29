package com.woniu.yoga.communicate.controller;

import com.woniu.yoga.commom.utils.JsonUtil;
import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.communicate.service.FollowService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
    * @Description 查看我的关注人列表或我的粉丝
    * @param state 传入0为查找我的粉丝,传入1位查找我的关注人列表
    * @param currentPage
    * @param pageSize
    * @param session
    * @author huijie yan
    * @date 2019/4/25
    * @return com.woniu.yoga.commom.vo.Result
    */
    @ApiOperation(value = "查看我的关注人列表或我的粉丝")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "state", value = "传入0查找我的粉丝,传入1查找我的关注人列表", required = true, paramType = "path"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条数据", required = true, paramType = "path")
    })
    @GetMapping("/{state}/{currentPage}/{pageSize}/showFollowList")
    public Result showFollowList(@PathVariable("state") Integer state, @PathVariable("currentPage") Integer currentPage,
                                 @PathVariable("pageSize") Integer pageSize, HttpSession session){
        return followService.showFollowList(state, currentPage, pageSize, session);
    }

    /**
    * @Description 查看我的关注人发表的动态
    * @param
    * @author huijie yan
    * @date 2019/4/24
    * @return com.woniu.yoga.commom.vo.Result
    */
    @ApiOperation(value = "查看我的关注人发表的动态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条数据", required = true, paramType = "path")
    })
    @GetMapping("/{currentPage}/{pageSize}/showFollowHomepage")
    public Result showFollowHomepage(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize, HttpSession session){
        return followService.showFollowHomepage(currentPage, pageSize, session);
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
    @PutMapping("/{userId}/addFollow")
    public Result addFollow(@PathVariable("userId") Integer userId, HttpSession session){
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
    @DeleteMapping("/{userId}/cancelFollow")
    public String cancelFollow(@PathVariable("userId") Integer userId, HttpSession session){
        return JsonUtil.toJson(followService.cancelFollow(userId, session));
    }
}
