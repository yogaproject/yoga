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
@Controller
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
            @ApiImplicitParam(name = "mid", value = "动态的id", required = true, paramType = "path"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条数据", required = true, paramType = "path")
    })
    @GetMapping("/{mid}/{currentPage}/{pageSize}/showComments")
    @ResponseBody
    public Result<CommentVo> showComments(@PathVariable("mid") Integer mid, @PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize){
        return commentService.showComments(mid,currentPage,pageSize);
    }

    /**
     * @Description 添加评论
     * @param comment
     * @author huijie yan
     * @date 2019/4/22
     * @return com.woniu.yoga.commom.vo.Result
     */
    @ApiOperation(value = "添加评论")
    @ApiImplicitParam(name = "comment", value = "评论", required = true, paramType = "path")
    @PutMapping("/{comment}/addComment")
    @ResponseBody
    public Result addComment(@PathVariable("comment") Comment comment, HttpSession session){
        return commentService.addComment(comment, session);
    }

    @ApiOperation(value = "删除评论")
    @ApiImplicitParam(name = "commentId", value = "评论id", required = true, paramType = "path")
    @DeleteMapping("/{commentId}/deleteComment")
    @ResponseBody
    public Result deleteComment(@PathVariable("commentId") Integer commentId){
        return commentService.deleteComment(commentId);
    }
}
