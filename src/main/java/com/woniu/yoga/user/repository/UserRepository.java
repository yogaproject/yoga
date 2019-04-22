package com.woniu.yago.repository;

import com.woniu.yago.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: java类作用描述
 * @Author: lxy
 * @time: 2019/4/16 19:44
 */
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
    //注册登录手机，激活状态
    @Modifying
    @Query("update User u set u.active=1 where u.userPhone=?1")
    Integer activeUserByPhone(String userPhone);
    //注册邮箱时，激活状态
    @Modifying
    @Query("update User u set u.active=1 where u.userEmail=?1")
    Integer activeUserByEmail(String userEmail);
    //查看邮箱是否存在
    @Query("select u from User u where u.userEmail=?1")
    User queryUserByEmail(String userEmail);
    //注册查找判断邮箱
    @Query("select u from User u where u.userEmail=?1 and u.userVerifyCode=?2")
    User queryUserByEmailAndCode(String userEmail, String userVerifyCode);
    //登录查找判断邮箱
    @Query("select u from User u where u.userEmail=?1 and u.userPwd=?2 and u.active=1")
    User queryUserByEmailAndPwd(String userEmail,String userPwd);
    //查看手机是否存在
    @Query("select u from User u where u.userPhone=?1")
    User queryUserByPhone(String userPhone);
    //注册手机号，查看手机号，密码是否相同
    @Query("select u from User u where u.userPhone=?1 and u.userPwd=?2")
    User queryUserByPhoneAndPwd(String userPhone,String userPwd);
    //登录更新验证码
    @Modifying
    @Query("update User u set u.userVerifyCode=:userVerifyCode where u.userPhone=:userPhone")
    Integer updateCode(@Param("userVerifyCode")String userVerifyCode,@Param("userPhone")String userPhone);
    //登录手机号
    @Query("select u from User u where u.userPhone=?1 and u.userVerifyCode=?2")
    User queryUserByPhoneAndCode(String userPhone,String userVerifyCode);


}
