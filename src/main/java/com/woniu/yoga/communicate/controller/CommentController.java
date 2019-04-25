package com.woniu.yoga.communicate.controller;

import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.communicate.pojo.Comment;
import com.woniu.yoga.communicate.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * @param m_id
     * @param currentPage
     * @param pageSize
     * @author huijie yan
     * @date 2019/4/22
     * @return com.woniu.yoga.commom.vo.Result
     */
    @RequestMapping("/showComments")
    public Result showComments(Integer m_id, @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage, @RequestParam(value = "pageSize", required = false, defaultValue = "10")Integer pageSize){
        return commentService.showComments(m_id,currentPage,pageSize);
    }

    /**
     * @Description 添加评论
     * @param comment
     * @author huijie yan
     * @date 2019/4/22
     * @return com.woniu.yoga.commom.vo.Result
     */
    @RequestMapping("/addComment")
    public Result addComment(Comment comment, HttpSession session){
        return commentService.addComment(comment, session);
    }
}
