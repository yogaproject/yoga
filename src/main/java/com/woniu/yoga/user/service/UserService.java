package com.woniu.yoga.user.service;

import com.woniu.yoga.manage.pojo.Coupon;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.vo.Result;
import com.woniu.yoga.user.vo.SearchConditionVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author liufeng
 * @ClassName StudentService
 * @Date 2019/4/18 15:30
 * @Version 1.0
 * @Description
 **/
@Service
public interface UserService {

    /*
     * @Author liufeng
     * @Date
     * @Description //根据条件查询订单
     * @Param
     * @return
     **/
    Result listOrder(Integer userId, @RequestParam(required = false, defaultValue = "所有订单") String orderStatus);

    /*
     * @Author liufeng
     * @Date
     * @Description //根据用户id查找有效的订单
     * @Param
     * @return
     **/
    Result listCouponsByUserId(Integer userId);

    Result listAroundCoachs(SearchConditionVO searchConditionVO) throws RuntimeException;

    Result listAroundVenues(SearchConditionVO searchConditionVO) throws RuntimeException;

    Result getDetailInfoByUserId(Integer userId, Integer coachId) throws RuntimeException;
    //查询用户优惠券
    List<Coupon> fandCouponByUserId(int userid) throws RuntimeException;

    //插入 lxy
    void saveUser(User user);
    //邮箱注册验证 lxy
    boolean sendRegEmailCode(User user);
    //查询邮箱是否存在 lxy
    User queryUserByEmail(String userEmail);
    //查询手机是否存在 lxy
    User queryUserByPhone(String userPhone);
    //注册，登录发送验证码（密码），并插入redis lxy
    boolean sendPhoneMessage(User user,Integer templateId);

    Result getVenueDetailInfoByUserId(Integer userId) throws RuntimeException;

    Result getAllMyInfos(Integer userId);

    Result getAllMyFans(Integer userId);

    Result getAllMyFocus(Integer userId);

    Result getAllMyComments(Integer userId);

    List<Coupon> selectCouponByUserId(int userId);
}
