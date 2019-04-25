package com.woniu.yoga.user.controller;


import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.service.serviceImpl.UserServiceImpl;
import com.woniu.yoga.user.util.MailUtil;
import com.woniu.yoga.user.util.PhoneUtil;
import com.woniu.yoga.user.util.RegexpUtil;
import com.woniu.yoga.user.util.ResultUtil;
import com.woniu.yoga.user.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


/**
 * @Author liufeng
 * @ClassName StudentService
 * @Date 2019/4/18 15:30
 * @Version 1.0
 * @Description 用于处理用户与后台的交互
 **/
@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    /*
     * @Author liufeng
     * @Date
     * @Description //根据订单状态，查询用户订单
     * @Param
     *  String orderStatus：订单状态，默认为“所有订单”，只能为“未完成订单”，“已完成”，“所有订单”，其余为非法
     * @return
     *  返回查询到的订单结果集合，参数不合法返回空
     **/
    public Result listOrder(HttpSession session, String orderStatus) {
        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();
        return userService.listOrder(userId,orderStatus);
    }

    /*
     * @Author liufeng
     * @Date
     * @Description //根据用户Id查找可用的优惠券
     * @Param
     *  Integer userId：用户id
     * @return
     *  返回可用优惠券的集合
     **/
    public Result listCouponsByUserId(HttpSession session) {
        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();
        return userService.listCouponsByUserId(userId);
    }
    /**
     * @Description: java类作用描述
     * 用户填写相关信息，点击注册按钮
     * 系统先将用户记录保存到数据库中，其中用户状态为未激活
     * 系统发送一封邮件并通知用户去验证
     * 用户登录邮箱并点击激活链接
     * 系统将用户状态更改为已激活并通知用户注册成功
     * @Author: 路边
     * @time: 2019/4/16 10:25
     */
    //-----------------------------------邮箱方式-----------------------------

    /**
     * 方法实现说明 邮箱发送密码邮件到email
     * @author      lxy
     * @Param:      email，password，userName
     * @return      json String "success" "fail"
     * @exception
     * @date        2019/4/17 16:26
     */
    @RequestMapping(value = "/sendRegEmailCode")
    @ResponseBody
    public String sendRegEmailCode(String userEmail,User user){
        if(userEmail.equals("")){
            return ResultUtil.errorOperation("不能为空").getMessage();
        }
        if(!user.getUserEmail().matches(RegexpUtil.RegExp_Mail)) {
            return ResultUtil.errorOperation("邮箱格式不匹配").getMessage();
        }
        if (userService.queryUserByEmail(userEmail)!=null){
            return ResultUtil.errorOperation("已存在").getMessage();
        }
        user.setUserEmail(userEmail);
        if (userService.sendRegEmailCode(user)){
            return ResultUtil.actionSuccess("前往邮箱获取密码",user).getMessage();
        }else {
            return ResultUtil.connectDatabaseFail().getMessage();
        }
    }
    /**
     * 方法实现说明 注册邮箱，从邮箱中获取验证码，输入密码，验证密码和邮箱、验证码，
     * @author      lxy
     * @Param:      userVerifyCode，userPhone,activa
     * @return      json String
     * @exception
     * @date        2019/4/19 17:22
     */
    @RequestMapping("regByEmail")
    @ResponseBody
    public String regByEmail(String userEmail,String userPwd,String userVerifyCode,User user,HttpSession session){
        if (userPwd.equals("") || userEmail.equals("") || userVerifyCode.equals("")){
            return ResultUtil.errorOperation("不能为空").getMessage();
        }
        if(!userEmail.matches(RegexpUtil.RegExp_Mail)) {
            return ResultUtil.errorOperation("邮箱格式不匹配").getMessage();
        }
        if (!userPwd.matches(RegexpUtil.RegExp_PASS)){
            return ResultUtil.errorOperation("密码格式不匹配").getMessage();
        }
        user=userService.queryUserByEmailAndCode(userEmail,userVerifyCode);
        if (user!=null){
            user.setUserPwd(userPwd);
            if (userService.activeUserByEmail(userEmail)){
                session.setAttribute("user",user);
                return ResultUtil.actionSuccess("注册成功",user).getMessage();
            }else {
                return ResultUtil.errorOperation("注册失败").getMessage();
            }
        }else {
            return ResultUtil.errorOperation("已存在").getMessage();
        }
    }

    /**
     * 方法实现说明 邮箱以验证密码登录
     * @author      lxy
     * @Param:      userEmail，userPwd
     * @return
     * @exception
     * @date        2019/4/19 21:37
     */
    @RequestMapping(value = "/loginByEmailAndPwd")
    @ResponseBody
    public String loginByEmailAndPwd(String userEmail, String userPwd, User user, HttpSession session){
        if (userEmail.equals("") || userPwd.equals("")){
            return ResultUtil.errorOperation("不能为空").getMessage();
        }
        user= userService.queryUserByEmailAndPwd(userEmail,userPwd);
        if (user!=null){
            session.setAttribute("user",user);
            return ResultUtil.actionSuccess("登录成功",user).getMessage();
        }else {
            return ResultUtil.errorOperation("登录失败").getMessage();
        }
    }
    /**
     * 方法实现说明  邮箱找回密码，通过用户名发送邮件找回密码
     * @author      lxy
     * @Param:      userEmail，userPwd
     * @return      String json
     * @exception
     * @date        2019/4/21 3:32
     */
    @RequestMapping(value = "/getOldEmailPwd")
    @ResponseBody
    public String getOldEmailPwd(String userEmail,User user){
        if(userEmail.equals("")){
            return ResultUtil.errorOperation("不能为空").getMessage();
        }
        if(!user.getUserEmail().matches(RegexpUtil.RegExp_Mail)) {
            return ResultUtil.errorOperation("邮箱格式不匹配").getMessage();
        }
        user=userService.queryUserByEmail(userEmail);
        String content = "<html><head></head><body><h1>这是一封绝密邮件,不要随便将内容透露给别人。" +
                "</h1><br><h3>您的原密码为：" + user.getUserPwd() + "。</h3></body></html>";
        if (user!=null){
            new Thread(new MailUtil(userEmail,user.getUserPwd(),content)).start();
            return ResultUtil.actionSuccess("前往邮箱获取密码",user).getMessage();
        }else {
            return ResultUtil.errorOperation("邮箱不存在").getMessage();
        }

    }




    //-----------------------------------手机方式-----------------------------
    /**
     * 方法实现说明
     * @author      lxy 手机点击发送密码，插入密码和手机号，状态为0
     * @Param:      userVerifyCode，userPhone
     * @return      json String "0","3"
     * @exception
     * @date        2019/4/20 9:28
     */
    @RequestMapping("sendRegPhonePwd")
    @ResponseBody
    public String sendRegPhonePwd(User user) {
        if(user.getUserPhone().equals("") || user==null){
            return ResultUtil.errorOperation("不能为空").getMessage();
        }
        if(!user.getUserPhone().matches(RegexpUtil.RegExp_PHONE)) {
            return ResultUtil.errorOperation("手机格式不匹配").getMessage();
        }
        if (userService.queryUserByPhone(user.getUserPhone())!=null){
            return ResultUtil.errorOperation("手机号已存在").getMessage();
        }
        if(userService.sendRegPhonePwd(user)){
            return ResultUtil.actionSuccess("请在手机上查收密码",user).getMessage();
        }else {
            return ResultUtil.connectDatabaseFail().getMessage();
        }
    }
    /**
     * 方法实现说明 注册手机号，输入密码，验证密码和手机号，查找，更新，激活状态0--->1
     *
     * @author      lxy
     * @Param:      userVerifyCode，userPhone,activa
     * @return      json String "0","1","3"
     * @exception
     * @date        2019/4/19 17:22
     */
    @RequestMapping("regByPhone")
    @ResponseBody
    public String regByPhone(String userPhone,String userPwd,User user,HttpSession session){
        if (userPwd.equals("") || userPhone.equals("")){
            return ResultUtil.errorOperation("不能为空").getMessage();
        }
        if(!userPhone.matches(RegexpUtil.RegExp_PHONE)) {
            return ResultUtil.errorOperation("手机格式不匹配").getMessage();
        }
        user=userService.queryUserByPhoneAndPwd(userPhone,userPwd);
        if (user!=null){
            if (userService.activeUserByPhone(userPhone)){
                session.setAttribute("user",user);
                return ResultUtil.actionSuccess("注册成功",user).getMessage();
            }else {
                return ResultUtil.errorOperation("注册失败").getMessage();
            }
        }else {
            return ResultUtil.errorOperation("已存在").getMessage();
        }
    }
    /**
     * 方法实现说明 点击发送验证码，更换验证码
     * @author      lxy
     * @Param:      userVerifyCode,userPhone
     * @return
     * @exception
     * @date        2019/4/20 16:18
     */
    @RequestMapping("sendLoginPhonePwd")
    @ResponseBody
    public String sendLoginPhonePwd(User user){
        if(user.getUserPhone().equals("") || user==null){
            return ResultUtil.errorOperation("不能为空").getMessage();
        }
        if(!user.getUserPhone().matches(RegexpUtil.RegExp_PHONE)) {
            return ResultUtil.errorOperation("手机格式不匹配").getMessage();
        }
        if(userService.sendLoginMessage(user)){
            return ResultUtil.actionSuccess("请在手机上查收验证码",user).getMessage();
        }else {
            return ResultUtil.connectDatabaseFail().getMessage();
        }
    }




    /**
     * 方法实现说明 验证更新的验证码和手机号，相同则登录，登陆后，激活为1
     * @author      lxy
     * @Param:      userVerifyCode,userPhone
     * @return      json String
     * @exception
     * @date        2019/4/20 16:15
     */
    @RequestMapping("/loginByPhoneAndCode")
    @ResponseBody
    public String loginByPhoneAndCode(String userPhone,String userVerifyCode,User user,HttpSession session){
        if (userVerifyCode.equals("") || userPhone.equals("")){
            return ResultUtil.errorOperation("不能为空").getMessage();
        }
        if(!user.getUserPhone().matches(RegexpUtil.RegExp_PHONE)) {
            return ResultUtil.errorOperation("手机格式不匹配").getMessage();
        }
        user=userService.queryUserByPhoneAndCode(userPhone,userVerifyCode);
        if (user!=null){
            if (userService.activeUserByPhone(userPhone)){
                session.setAttribute("user",user);
                return ResultUtil.actionSuccess("登录成功",user).getMessage();
            }else {
                return ResultUtil.connectDatabaseFail().getMessage();
            }
        }else {
            return ResultUtil.errorOperation("手机号不存在").getMessage();
        }
    }


    /**
     * 方法实现说明
     * @author      lxy 通过手机找回密码，返回登录页面
     * @Param:    userPhone
     * @return      String json
     * @exception
     * @date        2019/4/21 3:32
     */
    @RequestMapping(value = "/getOldPhonePwd")
    @ResponseBody
    public String getOldPhonePwd(String userPhone,User user){
        if(userPhone.equals("")){
            return ResultUtil.errorOperation("不能为空").getMessage();
        }
        if(!user.getUserPhone().matches(RegexpUtil.RegExp_PHONE)) {
            return ResultUtil.errorOperation("手机格式不匹配").getMessage();
        }
        user=userService.queryUserByPhone(userPhone);
        if (user!=null){
            new PhoneUtil().sendCode(user.getUserPhone(),user.getUserPwd());
            return ResultUtil.actionSuccess("请在手机上查收密码",user).getMessage();
        }else {
            return ResultUtil.errorOperation("手机号不存在").getMessage();
        }

    }


}
