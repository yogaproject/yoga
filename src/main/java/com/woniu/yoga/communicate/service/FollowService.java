package com.woniu.yoga.communicate.service;

import com.woniu.yoga.commom.vo.Result;

import javax.servlet.http.HttpSession;

public interface FollowService {

    Result showFollowList(Integer state, Integer currentPage, Integer pageSize, HttpSession session);

    Result showFollowHomepage(Integer currentPage, Integer pageSize, HttpSession session);

    Result addFollow(Integer userId, HttpSession session);

    Result cancelFollow(Integer userId, HttpSession session);
}
