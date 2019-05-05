package com.woniu.yoga.user.dao;

import com.woniu.yoga.manage.pojo.Coupon;
import com.woniu.yoga.pay.pojo.Wallet;
import com.woniu.yoga.user.dto.CoachDTO;
import com.woniu.yoga.user.dto.SearchConditionDTO;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.util.UserMapperProviderUtil;
import com.woniu.yoga.user.vo.CoachDetailInfoVO;
import com.woniu.yoga.user.vo.UserVO;
import com.woniu.yoga.user.vo.VenueVOR;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@Repository
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
    List<CoachDTO> listAroundCoach(SearchConditionDTO searchConditionDTO) throws SQLException;
    @SelectProvider(type = UserMapperProviderUtil.class,method = "listAroundVenues")
    List<VenueVOR> listAroundVenue(SearchConditionDTO searchConditionDTO) throws SQLException;

    /*
     * @Author liufeng
     * @Description //查询瑜伽师的详细信息
     **/
    @Select("select user.user_id userId,real_name,user_headimg,user_phone,user_qq,user_wechat,coach_detail,coach_id,coach_style,authentication,deal_account,good_comment,user_privacy,user_level level from user,coach where user.user_id = coach.user_id and user.user_id = #{userId} and user_flag = 0")
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
            @Result(column = "user_privacy", property = "privacy"),
            @Result(column = "user_level",property = "level"),
//            @Result(column = "coach_id", property = "courses", one = @One(
//                    select = "com.woniu.yoga.user.dao.CoachMapper.findCourseByCoachId"
//            )),
    })
    CoachDetailInfoVO getDetailInfoByUserId(Integer userId) throws SQLException;

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

    User queryUserByEmailAndPwd(String userEmail, String userPwd);

    User queryUserByPhone(String userPhone);

    User queryUserByPhoneAndPwd(String userPhone, String userPwd);

    User queryUserByPhoneAndCode(String userPhone, String userVerifyCode);

    Integer updateCode(String userVerifyCode, String userPhone);

    User queryUserByEmailAndCode(String userEmail, String userVerifyCode);

    @Select("select user_phone from user where user_id = #{userId}")
    String selectPhoneByUserId(Integer userId);
    @Select("select * from coupon where coupon_id in (select coupon_id from user_coupon where user_id=#{userid}) and now() >= effective_date and now() < expiration_date")
    List<Coupon> findCouponByUserId(@Param("userid") int userid) throws SQLException;

    @Select("select user_nickname nickname,user_headimg img,user_level level from user where user_id = #{userId}" )
    UserVO findBriefInfoByUserId(Integer userId) throws SQLException;
}