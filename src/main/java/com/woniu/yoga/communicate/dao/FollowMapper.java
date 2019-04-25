package com.woniu.yoga.communicate.dao;

import com.woniu.yoga.communicate.pojo.Follow;
import com.woniu.yoga.communicate.vo.FollowVo;
import com.woniu.yoga.home.vo.HomepageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FollowMapper {
    int deleteByPrimaryKey(Integer followId);

    int insert(Follow record);

    int insertSelective(Follow record);

    Follow selectByPrimaryKey(Integer followId);

    int updateByPrimaryKeySelective(Follow record);

    int updateByPrimaryKey(Follow record);

    List<FollowVo> queryFans(@Param("userId") Integer userId, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    List<FollowVo> queryFollowList(@Param("userId") Integer userId, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    List<HomepageVo> queryFollowHomepages(@Param("userId") Integer userId, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    int selectIfFollow(@Param("userId") Integer userId, @Param("followedId") Integer followedId);

    void updateFollowStatus(@Param("userId") Integer userId, @Param("followedId") Integer uid);

    int selectFollowStatus(@Param("userId") Integer userId, @Param("followedId") Integer followedId);

    void updateCancelFollowStatus(@Param("userId") Integer userId, @Param("followedId") Integer followedId);

    void deleteByFlag(@Param("userId") Integer userId, @Param("followedId") Integer userId1);
}