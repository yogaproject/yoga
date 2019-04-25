package com.woniu.yoga.communicate.controller;

import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.communicate.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("/showFollowList")
    public Result showFollowList(Integer state, @RequestParam(value = "currentPage",required = false,defaultValue = "1") Integer currentPage,
                                 @RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize, HttpSession session){
        return followService.showFollowList(state, currentPage, pageSize, session);
    }

    /**
    * @Description 查看我的关注人发表的动态
    * @param
    * @author huijie yan
    * @date 2019/4/24
    * @return com.woniu.yoga.commom.vo.Result
    */
    @RequestMapping("/showFollowHomepage")
    public Result showFollowHomepage(@RequestParam(value = "currentPage",required = false,defaultValue = "1") Integer currentPage,
                                     @RequestParam(value = "pageSize", required = false,defaultValue = "10") Integer pageSize, HttpSession session){
        return followService.showFollowHomepage(currentPage, pageSize, session);
    }

    /**
    * @Description 关注用户
    * @param userId
    * @author huijie yan
    * @date 2019/4/24
    * @return com.woniu.yoga.commom.vo.Result
    */
    @RequestMapping("/addFollow")
    public Result addFollow(Integer userId, HttpSession session){
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
    @RequestMapping("/cancelFollow")
    public Result cancelFollow(Integer userId, HttpSession session){
        return followService.cancelFollow(userId, session);
    }
}
