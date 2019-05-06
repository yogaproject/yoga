package com.woniu.yoga.communicate.dao;

import com.woniu.yoga.communicate.pojo.Follow;
import com.woniu.yoga.communicate.vo.FollowVo;
import com.woniu.yoga.communicate.vo.MyVo;
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

    List<FollowVo> queryFans(@Param("userId") Integer userId);

    List<FollowVo> queryFollowList(@Param("userId") Integer userId);

    List<HomepageVo> queryFollowHomepages(@Param("userId") Integer userId);

    //查找是否关注
    int selectIfFollow(@Param("userId") Integer userId, @Param("followedId") Integer followedId);

    //更新关注状态
    void updateFollowStatus(@Param("userId") Integer userId, @Param("followedId") Integer uid);

    //查找关注状态
    int selectFollowStatus(@Param("userId") Integer userId, @Param("followedId") Integer followedId);

    //更新取关状态
    void updateCancelFollowStatus(@Param("userId") Integer userId, @Param("followedId") Integer followedId);

    //通过软删除取关
    void deleteByFlag(@Param("userId") Integer userId, @Param("followedId") Integer userId1);

    //通过昵称模糊查询关注用户
    List<FollowVo> queryFollowUser(@Param("userId") Integer userId, @Param("userNickName") String userNickName);

    //查找我的粉丝个数
    Integer selectFansCount(Integer userId);

    //查找我的关注个数
    Integer selectFollowCount(Integer userId);

    //查找我的昵称，等级，头像
    MyVo selectMyVo(Integer userId);

    //查找我发布的动态数量
    Integer selectHomepageCount(Integer userId);
}