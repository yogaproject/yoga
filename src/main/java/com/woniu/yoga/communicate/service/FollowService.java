package com.woniu.yoga.communicate.service;

import com.woniu.yoga.home.vo.Result;

import javax.servlet.http.HttpSession;

public interface FollowService {

    Result showFollowList(Integer state, HttpSession session);

    Result showFollowHomepage(HttpSession session);

    Result addFollow(Integer userId, HttpSession session);

    Result cancelFollow(Integer userId, HttpSession session);

    Result searchFollow(String userNickName, Integer userId);
}
