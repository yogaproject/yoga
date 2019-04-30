package com.woniu.yoga.user.service;

import com.woniu.yoga.manage.pojo.Coupon;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.vo.Result;
import com.woniu.yoga.user.vo.SearchConditionVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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


    //登录注册，插入用户信息 lxy
    User saveUser(User user);
    //邮箱注册验证 lxy
    boolean sendRegEmailCode(User user);
    //登录注册手机，激活状态 lxy
    boolean activeUserByPhone(String userPhone);
    //登录注册邮箱，激活状态 lxy
    boolean activeUserByEmail(String userEmail);
    //查询邮箱是否存在 lxy
    User queryUserByEmail(String userEmail);
    //邮箱登录，查询邮箱和密码 lxy
    User queryUserByEmailAndPwd(String userEmail,String userPwd);
    //邮箱注册，查询邮箱和验证码 lxy
    User queryUserByEmailAndCode(String userEmail, String userVerifyCode);
    //查询手机是否存在 lxy
    User queryUserByPhone(String userPhone);
    //注册发送验证码，并插入 lxy
    boolean sendRegPhonePwd(User user);
    //已发送验证码，注册手机，查询用户名 lxy
    User queryUserByPhoneAndPwd(String userPhone, String userPwd);
    //登录发送验证码 lxy
    boolean sendLoginMessage(User user);
    //登录手机，查询验证手机和验证码 lxy
    User queryUserByPhoneAndCode(String userPhone, String userVerifyCode);
    //查询用户优惠券
    List<Coupon> fandCouponByUserId(int userid);
}
