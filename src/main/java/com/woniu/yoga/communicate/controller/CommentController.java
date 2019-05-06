package com.woniu.yoga.communicate.controller;

import com.woniu.yoga.communicate.pojo.Comment;
import com.woniu.yoga.communicate.service.CommentService;
import com.woniu.yoga.communicate.vo.CommentVo;
import com.woniu.yoga.home.vo.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author guochxi
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    /**
     * @Description 查看评论
     * @param mid
     * @param currentPage
     * @param pageSize
     * @author huijie yan
     * @date 2019/4/22
     * @return com.woniu.yoga.commom.vo.Result
     */
    @ApiOperation(value = "查看动态内容里的评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mid", value = "动态的id", required = true, paramType = "body")
    })
    @PostMapping("/showComments")
    public Result<CommentVo> showComments(@RequestBody Integer mid){
        return commentService.showComments(mid);
    }

    /**
     * @Description 添加评论
     * @param comment
     * @author huijie yan
     * @date 2019/4/22
     * @return com.woniu.yoga.commom.vo.Result
     */
    @ApiOperation(value = "添加评论")
    @ApiImplicitParam(name = "comment", value = "评论", required = true, paramType = "body")
    @PutMapping("/addComment")
    public Result addComment(@RequestBody Comment comment, HttpSession session){
        return commentService.addComment(comment, session);
    }

    @ApiOperation(value = "删除评论")
    @ApiImplicitParam(name = "commentId", value = "评论id", required = true, paramType = "body")
    @DeleteMapping("/deleteComment")
    public Result deleteComment(@RequestBody Integer commentId){
        return commentService.deleteComment(commentId);
    }
}
