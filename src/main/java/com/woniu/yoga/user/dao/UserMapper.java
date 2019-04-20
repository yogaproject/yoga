package com.woniu.yoga.user.dao;

import com.woniu.yoga.user.dto.SearchConditionDTO;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.util.UserMapperProviderUtil;
import com.woniu.yoga.user.vo.UserDetailInfoVo;
import com.woniu.yoga.user.vo.UserVO;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //根据地址查询附近的教练
    @SelectProvider(type = UserMapperProviderUtil.class, method = "findAroundCoachs")
    List<UserVO> listAroundUser(SearchConditionDTO searchConditionDTO);
    //查询用户的详细信息,mapper ???
    UserDetailInfoVo getDetailInfoByUserId(Integer userId);
    //根据条件查询周边的瑜伽师
    List<UserVO> listAroundCoach(SearchConditionDTO searchConditionDTO);
    //根据条件查询周边的场馆
    List<UserVO> listAroundVenue(SearchConditionDTO searchConditionDTO);
    //根据用户等级查询所享有的优惠
    @Select("select discount from level_discount where level_id = #{userLevel}")
    int selectDiscountByLevel(Integer userLevel);
}