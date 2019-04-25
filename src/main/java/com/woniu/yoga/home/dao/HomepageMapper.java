package com.woniu.yoga.home.dao;

import com.woniu.yoga.home.pojo.Homepage;
import com.woniu.yoga.home.vo.HomepageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HomepageMapper {
    int deleteByPrimaryKey(Integer mId);

    int insert(Homepage record);

    //动态发布动态
    int insertSelective(Homepage record);

    Homepage selectByPrimaryKey(Integer mId);

    int updateByPrimaryKeySelective(Homepage record);

    int updateByPrimaryKey(Homepage record);

    //根据用户的地址，显示附近的动态消息
    List<HomepageVo> queryHomepages(@Param("latitude") Float latitude, @Param("longitude") Float longitude, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    //根据动态消息的id，查询图文详情
    HomepageVo selectHomepageDetail(@Param("mid") Integer mid);

    List<HomepageVo> queryOtherHomepages(@Param("roleId") Integer roleId, @Param("latitude") Float latitude,
                                         @Param("longitude") Float longitude, @Param("currentPage") Integer currentPage,
                                         @Param("pageSize") Integer pageSize);
}