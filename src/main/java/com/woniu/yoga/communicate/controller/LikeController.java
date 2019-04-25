package com.woniu.yoga.communicate.controller;

import com.woniu.yoga.commom.utils.JsonUtil;
import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.communicate.service.LikeService;
import com.woniu.yoga.user.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Description 点赞控制层
 * @Author guochxi
 * @Date 2019/4/24 18:58
 * @Version 1.0
 */
@RestController
@RequestMapping("/like")
@Slf4j
public class LikeController {
    @Autowired
    private LikeService likeService;

    /**
     * @Description 点赞
     * @Author guochxi
     * @Date 20:36 2019/4/24
     * @Param [entityType, entityId, httpSession]
     * @return java.lang.String
     **/
    @PostMapping("/{entityType}/{entityId}/up")
    public String like(@PathVariable("entityType") Integer entityType,@PathVariable("entityId") Integer entityId, HttpSession httpSession){
        Object obj = httpSession.getAttribute("user");
        if(obj == null){
            return JsonUtil.toJson(Result.error("用户未登录"));
        }
        User user = (User) obj;
        try {
            long likeCount = likeService.like(user.getUserId(),entityType,entityId);
            //返回最新的点赞总数
            return JsonUtil.toJson(Result.success("点赞成功",likeCount));
        } catch (Exception e){
            log.error("【点赞异常】+{}",e.getMessage());
            return JsonUtil.toJson(Result.error("点踩异常，请稍后重试"));
        }
    }

    /**
     * @Description 点踩
     * @Author guochxi
     * @Date 20:37 2019/4/24
     * @Param [entityType, entityId, httpSession]
     * @return java.lang.String
     **/
    @PostMapping("/{entityType}/{entityId}/down")
    public String dislike(@PathVariable("entityType") Integer entityType,@PathVariable("entityId") Integer entityId, HttpSession httpSession){
        Object obj = httpSession.getAttribute("user");
        if(obj == null){
            return JsonUtil.toJson(Result.error("用户未登录"));
        }
        User user = (User) obj;
        try {
            long likeCount = likeService.dislike(user.getUserId(),entityType,entityId);
            //返回最新的点赞总数
            return JsonUtil.toJson(Result.success("点踩成功",likeCount));
        } catch (Exception e){
            log.error("【点踩异常】+{}",e.getMessage());
            return JsonUtil.toJson(Result.error("点赞异常，请稍后重试"));
        }
    }
}
