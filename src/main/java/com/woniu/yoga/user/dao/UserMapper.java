package com.woniu.yoga.user.dao;

import com.woniu.yoga.user.dto.SearchConditionDTO;
import com.woniu.yoga.user.pojo.Course;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.util.UserMapperProviderUtil;
import com.woniu.yoga.user.vo.CoachDetailInfoVO;
import com.woniu.yoga.user.vo.UserDetailInfoVo;
import com.woniu.yoga.user.vo.UserVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId)throws SQLException;

    int insert(User record)throws SQLException;

    int insertSelective(User record)throws SQLException;

    User selectByPrimaryKey(Integer userId)throws SQLException;

    int updateByPrimaryKeySelective(User record)throws SQLException;

    int updateByPrimaryKey(User record)throws SQLException;

    /*
     * @Author liufeng
     * @Description //根据地址查询附近的教练
     **/
    @SelectProvider(type = UserMapperProviderUtil.class, method = "findAroundCoachs")
    List<UserVO> listAroundUser(SearchConditionDTO searchConditionDTO)throws SQLException;

    /*
     * @Author liufeng
     * @Description //查询瑜伽师的详细信息
     **/
    @Select("select real_name,user_headimg,user_phone,user_qq,user_wechat,coach_detail,coach_id,coach_style,authentication,deal_account,good_comment,user_privacy where user.user_id = coach.user_id and user.user_id = #{userId} and user_flag = 0")
    @Results(value = {
            @Result(column = "real_name", property = "realName"),
            @Result(column = "user_headimg", property = "headImg"),
            @Result(column = "user_phone", property = "phone"),
            @Result(column = "user_qq", property = "qq"),
            @Result(column = "user_wechat", property = "wechat"),
            @Result(column = "coach_detail", property = "detail"),
            @Result(column = "coach_style", property = "style"),
            @Result(column = "authentication", property = "authentication"),
            @Result(column = "deal_account", property = "numberOfTrade"),
            @Result(column = "good_comment", property = "goodCommentCount"),
            @Result(column = "user_privacy",property = "privacy"),
            @Result(column = "coach_id", property = "courses", one = @One(
                    select = "com.woniu.yoga.user.dao.CoachMapper.findCourseByCoachId"
            )),
    })
    CoachDetailInfoVO getDetailInfoByUserId(Integer userId)throws SQLException;

    /*
     * @Author liufeng
     * @Description //根据用户等级查询所享有的优惠
     **/
    @Select("select discount from level_discount where level_id = #{userLevel}")
    BigDecimal selectDiscountByLevel(Integer userLevel)throws SQLException;


    User saveUser(User user)throws SQLException;

    Integer activeUserByEamil(String userEmail)throws SQLException;

    Integer activeUserByPhone(String userPhone)throws SQLException;

    User queryUserByEmail(String userEmail)throws SQLException;

    User queryUserByEmailAndPwd(String userEmail, String userPwd)throws SQLException;

    User queryUserByPhone(String userPhone)throws SQLException;

    User queryUserByPhoneAndPwd(String userPhone, String userPwd)throws SQLException;

    User queryUserByPhoneAndCode(String userPhone, String userVerifyCode)throws SQLException;

    Integer updateCode(String userVerifyCode, String userPhone)throws SQLException;

    User queryUserByEmailAndCode(String userEmail, String userVerifyCode)throws SQLException;

    @Select("select user_phone from user where user_id = #{userId}")
    String selectPhoneByUserId(Integer userId)throws SQLException;
}