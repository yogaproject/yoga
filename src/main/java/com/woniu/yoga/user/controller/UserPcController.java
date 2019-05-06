package com.woniu.yoga.user.controller;

import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.user.constant.SysConstant;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.service.UserService;
import com.woniu.yoga.user.util.CodeUtil;
import com.woniu.yoga.user.util.NickNameUtil;
import com.woniu.yoga.user.util.RegexpUtil;
import com.woniu.yoga.user.util.ResultUtil;
import com.woniu.yoga.user.vo.PhoneToken;
import com.woniu.yoga.venue.pojo.Venue;
import com.woniu.yoga.venue.service.VenueService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Description: java类作用描述 pc登录、注册
 * @Author: lxy
 * @time: 2019/4/16 10:25
 */
@Transactional
@Controller
@RequestMapping("/userPc")
public class UserPcController {
    @Autowired
    private UserService userService;

    @Autowired
    private VenueService venueService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    //-----------------------------------PC端方式-----------------------------
    /**
     * 方法实现说明  发送手机注册的验证码，已封装
     * @author      lxy
     * @Param:      userPwd，userPhone
     * @return      json对象 Result
     * @exception
     * @date        2019/4/20 9:28
     */
    @RequestMapping("/sendRegPhoneCode")
    @ResponseBody
    public Result sendRegPhoneCode(User user) {
        if("".equals(user.getUserPhone()) || user==null){
            return ResultUtil.errorOperation("手机号不能为空");
        }
        if(!user.getUserPhone().matches(RegexpUtil.RegExp_PHONE)) {
            return ResultUtil.errorOperation("手机格式不匹配");
        }
        if (userService.queryUserByPhone(user.getUserPhone())!=null){
            return ResultUtil.errorOperation("该手机号已经被绑定，请重新输入");
        }
        Integer templateId = 316608;
        if(!userService.sendPhoneMessage(user,templateId)){
            return ResultUtil.connectDatabaseFail();
        }
        return ResultUtil.actionSuccess("请在手机上查收验证码",user);

    }
    /**
     * 方法实现说明 注册手机号
     * @author      lxy
     * @Param:      userVerifyCode，userPhone,userPwd，active
     * @return      json对象 Result
     * @exception
     * @date        2019/4/19 17:22
     */
    @RequestMapping("/regByPhone")
    @ResponseBody
    public Result regByPhone(String userPhone, String userPwd, String userVerifyCode ,User user, HttpSession session,
                          Venue venue){
        System.out.println(userVerifyCode+userPwd);
        if ("".equals(userPwd)){
            return ResultUtil.errorOperation("密码不能为空");
        }
        if ("".equals(userPhone)){
            return ResultUtil.errorOperation("手机号不能为空");
        }
        if ("".equals(userVerifyCode)){
            return ResultUtil.errorOperation("验证码不能为空");
        }
        if(!userPhone.matches(RegexpUtil.RegExp_PHONE)) {
            return ResultUtil.errorOperation("手机格式不匹配，请重新输入");
        }
        if(!userPwd.matches(RegexpUtil.RegExp_PASS)){
            return ResultUtil.errorOperation("密码格式不匹配，请重新输入");
        }
         User exist=userService.queryUserByPhone(userPhone);
        if (exist!=null){
            return ResultUtil.errorOperation("该手机号已经被绑定，请重新输入");
        }
        if (!stringRedisTemplate.hasKey(userPhone)){
            return ResultUtil.errorOperation("验证码已过期，请重新获取验证码");
        }
        if(!stringRedisTemplate.opsForValue().get(userPhone).equals(userVerifyCode)){
            return ResultUtil.errorOperation("验证码错误，请重新输入");
        }
        user.setSalt(CodeUtil.userNumber());
        SimpleHash userHashPwd = new SimpleHash("MD5",userPwd,user.getSalt(),2);
        user.setUserPwd(userHashPwd.toString());
        user.setRoleId(3);
        user.setUserNickname(NickNameUtil.getRandomNickName());
        user.setActive(1);
        userService.saveUser(user);
        if (user.getActive()==0){
            return ResultUtil.errorOperation("注册失败");
        }
        if (user.getRoleId()==3){
            venue.setUserId(user.getUserId());
            venueService.saveVenue(venue);
        }
        PhoneToken token = new PhoneToken(userPhone);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        user = (User) subject.getPrincipal();
        session.setAttribute(SysConstant.CURRENT_USER,user);
        System.out.println(user);
        return ResultUtil.actionSuccess("注册成功",user);
    }



    /**
     * 方法实现说明 手机登录
     * @author      lxy
     * @Param:      userPwd,userPhone
     * @return      json对象 Result
     * @exception
     * @date        2019/4/20 16:15
     */
    @RequestMapping("/loginByPhoneAndPwd")
    @ResponseBody
    public Result loginByPhoneAndPwd(String userPhone,String userPwd,User user,HttpSession session){
        user=userService.queryUserByPhone(userPhone);
        if (user==null){
            return ResultUtil.errorOperation("该手机号没有被注册，请先登录再注册");
        }
        if("".equals(userPhone)){
            return ResultUtil.errorOperation("手机号不能为空");
        }
        if("".equals(userPwd)){
            return ResultUtil.errorOperation("密码不能为空");
        }
        if (!userPhone.matches(RegexpUtil.RegExp_PHONE)){
            return ResultUtil.errorOperation("手机格式不匹配，请重新输入");
        }
        if (!userPwd.matches(RegexpUtil.RegExp_PASS)){
            return ResultUtil.errorOperation("密码格式不匹配，请重新输入");
        }
        if (user.getRoleId()!=3 && user.getRoleId()!=4){
            return ResultUtil.errorOperation("该用户因权限无法登录Pc端，请重新输入");
        }
        System.out.println(userPhone);
        Subject subject= SecurityUtils.getSubject();
        if (!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(userPhone,userPwd);
            try {
                subject.login(token);
                //认证成功
                System.out.println("认证成功!");
                session.setAttribute(SysConstant.CURRENT_USER, user);
                return ResultUtil.actionSuccess("登录成功",user);
            } catch (UnknownAccountException uae) {
                return ResultUtil.errorOperation("手机号不正确,请重新输入手机号");
            } catch (IncorrectCredentialsException ice) {
                return ResultUtil.errorOperation("密码不正确,请重新输入密码");
            } catch (AuthenticationException ae) {
                return ResultUtil.errorOperation("登录失败");
            }
        }
        System.out.println("sssssssssssssssssssss"+user);
        return ResultUtil.actionSuccess("登录成功",user);
    }

    /**
     * 方法实现说明 注销
     * @author      lxy
     * @Param:
     * @return      json字符串  redirect:../loginPc.html
     * @exception
     * @date        2019/4/30 10:46
     */
    @RequestMapping("/logout")
    public String logout() {
        System.out.println("注销");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:../loginPc.html";
    }


}