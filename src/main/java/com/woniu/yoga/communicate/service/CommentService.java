package com.woniu.yoga.communicate.service;

import com.woniu.yoga.communicate.pojo.Comment;
import com.woniu.yoga.home.vo.Result;

import javax.servlet.http.HttpSession;

public interface CommentService {

    Result showComments(Integer mid);

    Result addComment(Comment comment, HttpSession session);

    Result deleteComment(Integer commentId);
}
