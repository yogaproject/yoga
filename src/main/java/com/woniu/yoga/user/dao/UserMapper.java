package com.woniu.yoga.user.dao;

import com.woniu.yoga.user.dto.SearchConditionDTO;
import com.woniu.yoga.user.pojo.Course;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.util.UserMapperProviderUtil;
import com.woniu.yoga.user.vo.CoachDetailInfoVO;
import com.woniu.yoga.user.vo.UserDetailInfoVo;
import com.woniu.yoga.user.vo.UserVO;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /*
     * @Author liufeng
     * @Description //根据地址查询附近的教练
     **/
    @SelectProvider(type = UserMapperProviderUtil.class, method = "findAroundCoachs")
    List<UserVO> listAroundUser(SearchConditionDTO searchConditionDTO);
    /*
     * @Author liufeng
     * @Description //查询瑜伽师的详细信息
     **/
    @Select("select real_name,user_headimg,user_phone,user_qq,user_wechat,coach_detail,coach_id,coach_style,authentication,deal_account,good_comment where user.user_id = coach.user_id and user.user_id = #{userId}")
    @Results(value = {
            @Result(column = "real_name",property = "realName"),
            @Result(column = "user_headimg",property = "headImg"),
            @Result(column = "user_phone",property = "phone"),
            @Result(column = "user_qq",property = "qq"),
            @Result(column = "user_wechat",property = "wechat"),
            @Result(column = "coach_detail",property = "detail"),
            @Result(column = "coach_style",property = "style"),
            @Result(column = "authentication",property = "authentication"),
            @Result(column = "deal_account",property = "numberOfTrade"),
            @Result(column = "good_comment",property = "goodCommentCount"),
            @Result(column = "coach_id",property = "courses",one = @One(
                    select = "com.woniu.yoga.user.dao.CoachMapper.findCourseByCoachId"
            )),
    })
    CoachDetailInfoVO getDetailInfoByUserId(Integer userId);
    /*
     * @Author liufeng
     * @Description //根据用户等级查询所享有的优惠
     **/
    @Select("select discount from level_discount where level_id = #{userLevel}")
    BigDecimal selectDiscountByLevel(Integer userLevel);


    User saveUser(User user);

    Integer activeUserByEamil(String userEmail);

    Integer activeUserByPhone(String userPhone);

    User queryUserByEmail(String userEmail);

    User queryUserByEmailAndPwd(String userEmail,String userPwd);

    User queryUserByPhone(String userPhone);

    User queryUserByPhoneAndPwd(String userPhone, String userPwd);

    User queryUserByPhoneAndCode(String userPhone, String userVerifyCode);

    Integer updateCode(String userVerifyCode,String userPhone);

    User queryUserByEmailAndCode(String userEmail, String userVerifyCode);


}