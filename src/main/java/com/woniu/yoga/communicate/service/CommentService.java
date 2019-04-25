package com.woniu.yoga.communicate.service;

import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.communicate.pojo.Comment;

import javax.servlet.http.HttpSession;

public interface CommentService {

    Result showComments(Integer mid, Integer currentPage, Integer pageSize);

    Result addComment(Comment comment, HttpSession session);
}
