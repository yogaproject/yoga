package com.woniu.yoga.user.controller;

import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.pay.pojo.Wallet;
import com.woniu.yoga.pay.service.WalletService;
import com.woniu.yoga.user.constant.SysConstant;
import com.woniu.yoga.user.pojo.Coach;
import com.woniu.yoga.user.pojo.Role;
import com.woniu.yoga.user.pojo.Student;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.service.CoachService;
import com.woniu.yoga.user.service.RoleService;
import com.woniu.yoga.user.service.StudentService;
import com.woniu.yoga.user.service.UserService;
import com.woniu.yoga.user.util.*;
import com.woniu.yoga.user.vo.PhoneToken;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description: java类作用描述 app用户登录、注册、个人信息、功能通用
 * @Author: lxy
 * @time: 2019/4/16 10:25
 */
@Transactional
@Controller
@RequestMapping("/userApp")
public class UserAppController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CoachService coachService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private WalletService walletService;

    //-----------------------------------邮箱方式-----------------------------

    /**
     * 方法实现说明 发送邮箱验证码，已封装
     * @author      lxy
     * @Param:      email，password，userName
     * @return      json对象 Result
     * @exception
     * @date        2019/4/17 16:26
     */
    @RequestMapping(value = "/sendRegEmailCode")
    @ResponseBody
    public Result sendRegEmailCode(@RequestBody User user){
        if("".equals(user.getUserEmail())){
            return ResultUtil.errorOperation("邮箱号不能为空");
        }
        if(!user.getUserEmail().matches(RegexpUtil.RegExp_Mail)) {
            return ResultUtil.errorOperation("邮箱格式不匹配");
        }
        if (userService.queryUserByEmail(user.getUserEmail())!=null){
            return ResultUtil.errorOperation("该邮箱号已被注册，请重新输入");
        }
        user.setUserEmail(user.getUserEmail());
        if (!userService.sendRegEmailCode(user)){
            return ResultUtil.connectDatabaseFail();
        }
        return ResultUtil.actionSuccess("前往邮箱获取验证码",user);

    }
    /**
     * 方法实现说明 注册邮箱，注册后直接通过shiro令牌登录
     * 插入默认的头像和昵称,插入userid到学员
     * @author      lxy
     * @Param:      userVerifyCode，userPhone,active
     * @return      json对象 Result
     * @exception
     * @date        2019/4/19 17:22
     */
    @RequestMapping("regByEmail")
    @ResponseBody
    public Result regByEmail(@RequestBody User user, HttpSession session, Student student, Wallet wallet){
        if ("".equals(user.getUserPwd())){
            return ResultUtil.errorOperation("密码不能为空");
        }
        if ("".equals(user.getUserEmail())){
            return ResultUtil.errorOperation("邮箱号不能为空");
        }
        if ("".equals(user.getUserVerifyCode())){
            return ResultUtil.errorOperation("验证码不能为空");
        }
        if(!user.getUserEmail().matches(RegexpUtil.RegExp_Mail)) {
            return ResultUtil.errorOperation("邮箱格式不匹配");
        }
        if (!user.getUserPwd().matches(RegexpUtil.RegExp_PASS)){
            return ResultUtil.errorOperation("密码格式不匹配");
        }
        User exit=userService.queryUserByEmail(user.getUserEmail());
        if (exit!=null){
            return ResultUtil.errorOperation("该邮箱号已被注册，请重新输入");
        }
        if(!stringRedisTemplate.hasKey(user.getUserEmail())){
            return ResultUtil.errorOperation("验证码过期，请重新发送验证码");
        }
        if (!stringRedisTemplate.opsForValue().get(user.getUserEmail()).equals(user.getUserVerifyCode())){
            return ResultUtil.errorOperation("验证码错误，请重新输入");
        }
        user.setUserVerifyCode("");
        user.setRoleId(1);
        user.setSalt(CodeUtil.userNumber());
        SimpleHash userHashPwd = new SimpleHash("MD5",user.getUserPwd(),user.getSalt(),2);
        user.setUserPwd(userHashPwd.toString());
        user.setUserNickname(NickNameUtil.getRandomNickName());
        user.setActive(1);
        userService.saveUser(user);
        if (user.getActive()==0){
            return ResultUtil.errorOperation("注册失败");
        }
        User newUser=userService.queryUserByEmail(user.getUserEmail());
        if (newUser==null || !newUser.getUserPwd().equals(userHashPwd.toString())){
            return ResultUtil.errorOperation("注册失败");
        }
        student.setUserId(user.getUserId());
        wallet.setUserId(user.getUserId());
        walletService.saveWallet(user.getUserId());
        studentService.saveStudent(student);
        session.setAttribute(SysConstant.CURRENT_USER,newUser);
        return ResultUtil.actionSuccess("注册成功",newUser);
    }

    /**
     * 方法实现说明 邮箱以密码登录,shiro认证
     * @author      lxy
     * @Param:      userEmail，userPwd
     * @return      json对象 Result
     * @exception
     * @date        2019/4/19 21:37
     */
    @RequestMapping(value = "/loginByEmailAndPwd")
    @ResponseBody
    public Result loginByEmailAndPwd(@RequestBody User user, HttpSession session){
        User exist=userService.queryUserByEmail(user.getUserEmail());
        if (exist==null){
            return ResultUtil.errorOperation("该邮箱号没有被注册，请先注册再登录");
        }
        if (exist.getRoleId()!=1 && exist.getRoleId()!=2){
            return ResultUtil.errorOperation("该用户因权限无法登录App端，请重新输入");
        }
        if(!user.getUserEmail().matches(RegexpUtil.RegExp_Mail)) {
            return ResultUtil.errorOperation("邮箱格式不匹配");
        }
        if(!user.getUserPwd().matches(RegexpUtil.RegExp_PASS)){
            return ResultUtil.errorOperation("密码格式不匹配");
        }
        SimpleHash userHashNewPwd = new SimpleHash("MD5",user.getUserPwd(),exist.getSalt(),2);
        if (!exist.getUserPwd().equals(userHashNewPwd.toString())){
            return ResultUtil.errorOperation("密码错误,请重新输入");
        }
        session.setAttribute(SysConstant.CURRENT_USER,exist);
        return ResultUtil.actionSuccess("登录成功",exist);
    }
    /**
     * 方法实现说明  发送验证码（邮箱找回密码）
     * @author      lxy
     * @Param:      userEmail，userVerifyCode
     * @return      json对象 Result
     * @exception
     * @date        2019/4/21 3:32
     */
    @RequestMapping(value = "/getCodeByEmail")
    @ResponseBody
    public Result getCodeByEmail(@RequestBody User user){
        if("".equals(user.getUserEmail())){
            return ResultUtil.errorOperation("邮箱号不能为空");
        }
        if(!user.getUserEmail().matches(RegexpUtil.RegExp_Mail)) {
            return ResultUtil.errorOperation("邮箱格式不匹配");
        }
        user=userService.queryUserByEmail(user.getUserEmail());
        if (user==null){
            return ResultUtil.errorOperation("该邮箱号不存在，请重新输入");
        }
        String userVerifyCode = CodeUtil.userNumber();
        String content = "<html><head></head><body><h1>这是一封绝密邮件,不要随便将内容透露给别人。" +
                "</h1><br><h3>您本次找回密码所需的验证码为：" + userVerifyCode + "。</h3></body></html>";
        stringRedisTemplate.opsForValue().set(user.getUserEmail(),userVerifyCode);
        if (!stringRedisTemplate.hasKey(user.getUserEmail())){
            return ResultUtil.connectDatabaseFail();
        }
        new Thread(new MailUtil(user.getUserEmail(),userVerifyCode,content)).start();
        stringRedisTemplate.expire(user.getUserEmail(),30, TimeUnit.SECONDS);
        return ResultUtil.actionSuccess("前往邮箱获取验证码",user);

    }
    /**
     * 方法实现说明 登录重置密码界面（邮箱找回密码）
     * @author      lxy
     * @Param:      userEmail，userVerifyCode，user
     * @return      json对象 Result
     * @exception
     * @date        2019/4/23 22:38
     */
    @RequestMapping("/getPwdByEmail")
    @ResponseBody
    public Result getPwdByEmail(@RequestBody User user,HttpSession session){
        if ("".equals(user.getUserEmail())){
            return ResultUtil.errorOperation("邮箱号不能为空");
        }
        if ("".equals(user.getUserVerifyCode())){
            return ResultUtil.errorOperation("验证码不能为空");
        }
        if(!user.getUserEmail().matches(RegexpUtil.RegExp_Mail)) {
            return ResultUtil.errorOperation("邮箱格式不匹配");
        }
        if (!stringRedisTemplate.hasKey(user.getUserEmail())){
            return ResultUtil.errorOperation("验证码已过期，请重新点击获取");
        }
        if (!stringRedisTemplate.opsForValue().get(user.getUserEmail()).equals(user.getUserVerifyCode())){
            return ResultUtil.errorOperation("验证码错误，请重新输入");
        }
        session.setAttribute(SysConstant.CURRENT_USER,user);
        return ResultUtil.actionSuccess("验证成功，跳转下一步",user);

    }
    /**
     * 方法实现说明 重置密码
     * @author      lxy
     * @Param:      userPwd,confirmPwd,user,session
     * @return      json对象 Result
     * @exception
     * @date        2019/4/24 0:03
     */
    @RequestMapping("/updateUserPwdByEmail")
    @ResponseBody
    public Result updateUserPwdByEmail(@RequestBody User user,@RequestBody String confirmPwd,HttpSession session){
        if ("".equals(user.getUserPwd())){
            return ResultUtil.errorOperation("密码不能为空");
        }
        if ("".equals(confirmPwd)){
            return ResultUtil.errorOperation("确认密码不能为空");
        }
        if (!user.getUserPwd().matches(RegexpUtil.RegExp_PASS) || !confirmPwd.matches(RegexpUtil.RegExp_PASS)){
            return ResultUtil.errorOperation("密码格式不匹配");
        }
        User sessionUser= (User) session.getAttribute(SysConstant.CURRENT_USER);
        if (!user.getUserPwd().equals(confirmPwd)){
            return ResultUtil.errorOperation("确认密码错误，请重新输入");
        }
        User newUser=userService.queryUserByEmail(sessionUser.getUserEmail());
        newUser.setSalt(CodeUtil.userNumber());
        SimpleHash userHashPwd = new SimpleHash("MD5",user.getUserPwd(),newUser.getSalt(),2);
        newUser.setUserPwd(userHashPwd.toString());
        return ResultUtil.actionSuccess("重置密码成功",newUser);

    }


    //-----------------------------------手机方式-----------------------------
    /**
     * 方法实现说明  发送手机注册的密码，已封装
     * @author      lxy
     * @Param:      userPwd，userPhone
     * @return      json对象 Result
     * @exception
     * @date        2019/4/20 9:28
     */
    @RequestMapping("/sendRegPhonePwd")
    @ResponseBody
    public Result sendRegPhonePwd(@RequestBody User user) {
        if("".equals(user.getUserPhone()) || user==null){
            return ResultUtil.errorOperation("手机号不能为空");
        }
        if(!user.getUserPhone().matches(RegexpUtil.RegExp_PHONE)) {
            return ResultUtil.errorOperation("手机格式不匹配");
        }
        if (userService.queryUserByPhone(user.getUserPhone())!=null){
            return ResultUtil.errorOperation("该手机号已经被绑定，请重新输入");
        }
        Integer templateId = 317053;
        if(!userService.sendPhoneMessage(user,templateId)){
            return ResultUtil.connectDatabaseFail();
        }
        return ResultUtil.actionSuccess("请在手机上查收密码",user);

    }
    /**
     * 方法实现说明 注册手机号
     * 插入默认的头像和昵称,判断角色，分别插入userid到学员，教练，场馆
     * @author      lxy
     * @Param:      userVerifyCode，userPhone,activa
     * @return      json对象 Result
     * @exception
     * @date        2019/4/19 17:22
     */
    @RequestMapping("/regByPhone")
    @ResponseBody
    public Result regByPhone(@RequestBody User user, HttpSession session,
                             Student student, Coach coach,Wallet wallet){
        if ("".equals(user.getUserPwd())){
            return ResultUtil.errorOperation("密码不能为空");
        }
        if ("".equals(user.getUserPhone())){
            return ResultUtil.errorOperation("手机号不能为空");
        }
        if (user.getRoleId()==0){
            return ResultUtil.errorOperation("职业不能为空");
        }
        if(!user.getUserPhone().matches(RegexpUtil.RegExp_PHONE)) {
            return ResultUtil.errorOperation("手机格式不匹配");
        }
        User exist=userService.queryUserByPhone(user.getUserPhone());
        if (exist!=null){
            return ResultUtil.errorOperation("该手机号已经被绑定，请重新输入");
        }
        if (!stringRedisTemplate.hasKey(user.getUserPhone())){
            return ResultUtil.errorOperation("密码已过期，请重新获取密码");
        }
        if(!stringRedisTemplate.opsForValue().get(user.getUserPhone()).equals(user.getUserPwd())){
            return ResultUtil.errorOperation("密码错误，请重新输入");
        }
        user.setSalt(CodeUtil.userNumber());
        SimpleHash userHashPwd = new SimpleHash("MD5",user.getUserPwd(),user.getSalt(),2);
        user.setUserPwd(userHashPwd.toString());
        user.setUserNickname(NickNameUtil.getRandomNickName());
        user.setActive(1);
        wallet.setUserId(user.getUserId());
        walletService.saveWallet(user.getUserId());
        userService.saveUser(user);
        if (user.getActive()==0){
            return ResultUtil.errorOperation("注册失败");
        }
        if (user.getRoleId()==1){
            student.setUserId(user.getUserId());
            studentService.saveStudent(student);
        }
        if (user.getRoleId()==2){
            coach.setUserId(user.getUserId());
            coachService.saveCoach(coach);
        }
        User newUser=userService.queryUserByPhone(user.getUserPhone());
        if (newUser==null || !newUser.getUserPwd().equals(userHashPwd.toString())){
            return ResultUtil.errorOperation("注册失败");
        }
        session.setAttribute(SysConstant.CURRENT_USER,newUser);
        return ResultUtil.actionSuccess("注册成功",newUser);
    }
    /**
     * 方法实现说明  发送登录手机的验证码，已封装
     * @author      lxy
     * @Param:      userVerifyCode,userPhone,user
     * @return      json对象 Result
     * @exception
     * @date        2019/4/20 16:18
     */
    @RequestMapping("/sendLoginPhonePwd")
    @ResponseBody
    public Result sendLoginPhonePwd(@RequestBody User user){
        if("".equals(user.getUserPhone()) || user==null){
            return ResultUtil.errorOperation("手机号不能为空");
        }
        if(!user.getUserPhone().matches(RegexpUtil.RegExp_PHONE)) {
            return ResultUtil.errorOperation("手机格式不匹配");
        }
        if (userService.queryUserByPhone(user.getUserPhone())==null){
            return ResultUtil.errorOperation("该手机号没有注册，请先注册再登录");
        }
        Integer templateId = 316608;
        if(!userService.sendPhoneMessage(user,templateId)){
            return ResultUtil.connectDatabaseFail();
        }
        return ResultUtil.actionSuccess("请在手机上查收验证码",user);

    }

    /**
     * 方法实现说明 手机登录,以验证码方式登录
     * @author      lxy
     * @Param:      userVerifyCode,userPhone
     * @return      json对象 Result
     * @exception
     * @date        2019/4/20 16:15
     */
    @RequestMapping("/loginByPhoneAndCode")
    @ResponseBody
    public Result loginByPhoneAndCode(@RequestBody User user,HttpSession session){
        System.out.println(user);
        if("".equals(user.getUserPhone()) || user==null){
            return ResultUtil.errorOperation("手机号不能为空");
        }
        if("".equals(user.getUserVerifyCode())){
            return ResultUtil.errorOperation("验证码不能为空");
        }
        if (!user.getUserPhone().matches(RegexpUtil.RegExp_PHONE)){
            return ResultUtil.errorOperation("手机格式不匹配");
        }
        User exist=userService.queryUserByPhone(user.getUserPhone());
        if (exist==null){
            return ResultUtil.errorOperation("该手机号没有注册，请先注册再登录");
        }
        if (exist.getRoleId()!=1 && exist.getRoleId()!=2){
            System.out.println(user.getRoleId());
            return ResultUtil.errorOperation("该用户因权限无法登录App端，请重新输入");
        }
        if (!stringRedisTemplate.hasKey(user.getUserPhone())){
            return ResultUtil.errorOperation("验证码已过期，请重新点击获取");
        }
        if (!stringRedisTemplate.opsForValue().get(user.getUserPhone()).equals(user.getUserVerifyCode())){
            return ResultUtil.errorOperation("验证码错误，请重新输入");
        }
        session.setAttribute(SysConstant.CURRENT_USER,exist);
        return ResultUtil.actionSuccess("登录成功",exist);

    }


    /**
     * 方法实现说明   手机发送找回密码的验证码，已封装
     * @author      lxy
     * @Param:      userPhone
     * @return      json对象 Result
     * @exception
     * @date        2019/4/21 3:32
     */
    @RequestMapping(value = "/getCodeByPhone")
    @ResponseBody
    public Result getCodeByPhone(@RequestBody User user){
        if("".equals(user.getUserPhone())){
            return ResultUtil.errorOperation("手机号不能为空");
        }
        if(!user.getUserPhone().matches(RegexpUtil.RegExp_PHONE)) {
            return ResultUtil.errorOperation("手机格式不匹配");
        }
        User exist=userService.queryUserByPhone(user.getUserPhone());
        if (exist!=null){
            return ResultUtil.errorOperation("手机号没有注册，请重新输入");
        }
        int templateId= 316608;
        if(!userService.sendPhoneMessage(user,templateId)){
            return ResultUtil.connectDatabaseFail();
        }
        return ResultUtil.actionSuccess("请在手机上查收验证码",exist);

    }

    /**
     * 方法实现说明 登录重置密码界面
     * @author      lxy
     * @Param:      userPhone，userVerifyCode
     * @return      json对象 Result
     * @exception
     * @date        2019/4/23 22:38
     */
    @RequestMapping("/getPwdByPhone")
    @ResponseBody
    public Result getPwdByPhone(@RequestBody User user,HttpSession session){
        if ("".equals(user.getUserPhone())){
            return ResultUtil.errorOperation("手机号不能为空");
        }
        if ("".equals(user.getUserVerifyCode())){
            return ResultUtil.errorOperation("验证码不能为空");
        }

        if(!user.getUserPhone().matches(RegexpUtil.RegExp_PHONE)) {
            return ResultUtil.errorOperation("手机格式不匹配");
        }
        User exist=userService.queryUserByPhone(user.getUserPhone());
        if (exist==null){
            return ResultUtil.errorOperation("手机号没有注册，请重新输入");
        }
        if (!stringRedisTemplate.hasKey(user.getUserPhone())){
            return ResultUtil.errorOperation("验证码已过期，请重新点击获取");
        }
        if (!stringRedisTemplate.opsForValue().get(user.getUserPhone()).equals(user.getUserVerifyCode())){
            return ResultUtil.errorOperation("验证码错误，请重新输入");
        }
        session.setAttribute(SysConstant.CURRENT_USER,user);
        return ResultUtil.actionSuccess("验证成功，跳转下一步",user);

    }
    /**
     * 方法实现说明 重置密码
     * @author      lxy
     * @Param:      userPwd，confirmPwd
     * @return      json对象 Result
     * @exception
     * @date        2019/4/24 0:03
     */
    @RequestMapping("/updateUserPwdByPhone")
    @ResponseBody
    public Result updateUserPwdByPhone(@RequestBody User user,@RequestBody String confirmPwd,HttpSession session){
        if ("".equals(user.getUserPwd())){
            return ResultUtil.errorOperation("密码不能为空");
        }
        if ("".equals(confirmPwd)){
            return ResultUtil.errorOperation("确认密码不能为空");
        }
        if (!user.getUserPwd().matches(RegexpUtil.RegExp_PASS) || !confirmPwd.matches(RegexpUtil.RegExp_PASS)){
            return ResultUtil.errorOperation("密码格式不匹配");
        }
        if (user.getUserPwd().equals(confirmPwd)){
            return ResultUtil.errorOperation("确认密码错误，请重新输入");
        }
        User sessionUser= (User) session.getAttribute(SysConstant.CURRENT_USER);
        User newUser=userService.queryUserByPhone(sessionUser.getUserPhone());
        newUser.setSalt(CodeUtil.userNumber());
        SimpleHash userHashPwd = new SimpleHash("MD5",user.getUserPwd(),newUser.getSalt(),2);
        newUser.setUserPwd(userHashPwd.toString());
        return ResultUtil.actionSuccess("重置密码成功",newUser);


    }

    /**
     * 方法实现说明
     * @author      lxy
     * @Param:
     * @return      json字符串  redirect:../loginApp.html
     * @exception
     * @date        2019/5/3 14:57
     */
    @RequestMapping("/logout")
    public String logout() {
        System.out.println("注销");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:../loginApp.html";
    }

//-----------------------学生信息------------------------



    /**
     * 方法实现说明   手机发送手机绑定的验证码
     * @author      lxy
     * @Param:      userPhone
     * @return      json对象 Result
     * @exception
     * @date        2019/4/21 3:32
     */
    @RequestMapping(value = "/sendBindPhoneCode")
    @ResponseBody
    public Result sendBindPhoneCode(@RequestBody User user,HttpSession session){
        if("".equals(user.getUserPhone())){
            return ResultUtil.errorOperation("手机号不能为空");
        }
        if(!user.getUserPhone().matches(RegexpUtil.RegExp_PHONE)) {
            return ResultUtil.errorOperation("手机格式不匹配");
        }
        User exist=userService.queryUserByPhone(user.getUserPhone());
        if (exist!=null){
            return ResultUtil.errorOperation("手机号已被绑定，请重新输入手机号");
        }
        int templateId = 316608;
        if(!userService.sendPhoneMessage(user,templateId)){
            return ResultUtil.connectDatabaseFail();
        }
        User userSession=(User)session.getAttribute(SysConstant.CURRENT_USER);
        user=userService.queryUserByEmail(userSession.getUserEmail());
        return ResultUtil.actionSuccess("请在手机上查收验证码",user);
    }



    /**
     * 方法实现说明 绑定手机
     * @author      lxy
     * @Param:      userPhone,userVerifyCode
     * @return      json对象 Result
     * @exception
     * @date        2019/4/24 18:53
     */
    @RequestMapping("/bindPhone")
    @ResponseBody
    public Result bindPhone(@RequestBody User user,HttpSession session){
        if("".equals(user.getUserPhone())){
            return ResultUtil.errorOperation("手机号不能为空");
        }
        if ("".equals(user.getUserVerifyCode())){
            return ResultUtil.errorOperation("验证码不能为空");
        }
        if(!user.getUserPhone().matches(RegexpUtil.RegExp_PHONE)) {
            return ResultUtil.errorOperation("手机格式不匹配");
        }
        User exist=userService.queryUserByPhone(user.getUserPhone());
        if (exist!=null){
            return ResultUtil.errorOperation("手机号已被绑定，请重新输入手机号");
        }
        if (!stringRedisTemplate.hasKey(user.getUserPhone())){
            return ResultUtil.errorOperation("验证码已经过期，请重新点击获取");
        }
        if (!stringRedisTemplate.opsForValue().get(user.getUserPhone()).equals(user.getUserVerifyCode())){
            return ResultUtil.errorOperation("验证码错误，请重新输入");
        }
        User userSession= (User) session.getAttribute(SysConstant.CURRENT_USER);
        User existUser=userService.queryUserByEmail(userSession.getUserEmail());
        existUser.setUserPhone(user.getUserPhone());
        session.setAttribute(SysConstant.CURRENT_USER,existUser);
        return ResultUtil.actionSuccess("绑定手机成功",existUser);
    }

    /**
     * 方法实现说明 展现学生个人信息
     * @author      lxy
     * @Param:      user
     * @return      json对象 Result
     * @exception
     * @date        2019/4/24 16:17
     */
    @RequestMapping("/showStudentInfo")
    @ResponseBody
    public Result showStudentInfo(@RequestBody User user, HttpSession session){
        User userSession=(User)session.getAttribute(SysConstant.CURRENT_USER);
        if (userSession == null){
            return ResultUtil.errorOperation("展现信息失败");
        }
        if (userSession.getUserPhone()==null){
            user=userService.queryUserByEmail(userSession.getUserEmail());
            return ResultUtil.actionSuccess("展现信息成功",user);
        }
        user=userService.queryUserByPhone(userSession.getUserPhone());
        return ResultUtil.actionSuccess("展现信息成功",user);
    }


    /**
     * 方法实现说明  完善学生信息,user直接从前端传过来
     * @author      lxy
     * @Param:      user
     * @return      json对象 Result
     * @exception
     * @date        2019/4/24 15:52
     */

    @RequestMapping("/updateStudentInfo")
    @ResponseBody
    public Result updateStudentInfo(@RequestBody User user,HttpSession session){
        System.out.println(user+"修改信息------------------------");
        if ("".equals(user.getRealName())){
            return ResultUtil.errorOperation("请完善真实名字");
        }
        if ("".equals(user.getIdcard())){
            return ResultUtil.errorOperation("请完善身份证信息");
        }
        if (!user.getIdcard().matches(RegexpUtil.RegExp_ID)){
            return ResultUtil.errorOperation("身份证格式不匹配，请重新输入");
        }
        if (!user.getUserEmail().matches(RegexpUtil.RegExp_Mail)){
            return ResultUtil.errorOperation("邮箱格式不匹配，请重新输入");
        }
        if ("".equals(user.getUserHeadimg())){
            return ResultUtil.errorOperation("头像为空,请上传头像");
        }
        User exit=userService.queryUserByEmail(user.getUserEmail());
        if (exit!=null){
            return ResultUtil.errorOperation("该邮箱已经被绑定，请重新输入");
        }
        User userReal=userService.queryUserByEmail(user.getUserEmail());
        if (userReal==null){
            userReal=userService.queryUserByPhone(user.getUserPhone());
        }
        System.out.println(userReal);
        userReal.setIdcard(user.getIdcard());
        userReal.setUserNickname(user.getUserNickname());
        userReal.setUserEmail(user.getUserEmail());
        userReal.setUserPhone(user.getUserPhone());
        userReal.setUserHeadimg(user.getUserHeadimg());
        userReal.setUserPrivacy(user.getUserPrivacy());
        userReal.setRealName(user.getRealName());
        userReal.setSex(user.getSex());
        userReal.setUserQq(user.getUserQq());
        userReal.setUserWechat(user.getUserWechat());
        userReal.setUserLocation(user.getUserLocation());
        return ResultUtil.actionSuccess("成功完善信息",userReal);
    }
    /**
     * 方法实现说明 展现教练个人信息
     * @author      lxy
     * @Param:      user,coach,info
     * @return      json对象 Map<String,Object>
     * @exception
     * @date        2019/4/24 16:17
     */
    @RequestMapping("/showCoachInfo")
    @ResponseBody
    public Result showCoachInfo(@RequestBody User user,@RequestBody Coach coach,HttpSession session){
        User userSession=(User)session.getAttribute(SysConstant.CURRENT_USER);
        if (userSession == null){
            return ResultUtil.errorOperation("展现信息失败");
        }
        if ("".equals(userSession.getUserPhone())){
            user=userService.queryUserByEmail(userSession.getUserEmail());
        }
        user=userService.queryUserByPhone(userSession.getUserPhone());
        coach=coachService.findCoachByUserId(user.getUserId());
        Map<String,Object> map=new HashMap<>();
        map.put(SysConstant.CURRENT_USER,user);
        map.put(SysConstant.CURRENT_COACH,coach);
        return ResultUtil.actionSuccess("展现信息成功",map);
    }


    /**
     * 方法实现说明  完善教练信息,user直接从前端传过来
     * @author      lxy
     * @Param:      user,coach,info
     * @return      json对象 Map<String,Object>
     * @exception
     * @date        2019/4/24 15:52
     */

    @RequestMapping("/updateCoachInfo")
    @ResponseBody
    public Result updateCoachInfo(@RequestBody User user,@RequestBody Coach coach,HttpSession session,String info){
        User userSession= (User) session.getAttribute(SysConstant.CURRENT_USER);
        if (!"".equals(user.getUserEmail())){
            if (!user.getUserEmail().matches(RegexpUtil.RegExp_Mail)){
                return ResultUtil.errorOperation("邮箱格式不匹配，请重新输入");
            }
            User exit=userService.queryUserByEmail(user.getUserEmail());
            if (exit!=userService.queryUserByEmail(userSession.getUserEmail())&& exit!=null){
                return ResultUtil.errorOperation("该邮箱已经被绑定，请重新输入");
            }
        }
        if ("".equals(user.getRealName())){
            return ResultUtil.errorOperation("请完善真实名字");
        }
        if ("".equals(user.getIdcard()) ){
            return ResultUtil.errorOperation("请完善身份证信息");
        }
        if (!user.getIdcard().matches(RegexpUtil.RegExp_ID)){
            return ResultUtil.errorOperation("身份证格式不匹配，请重新输入");
        }
        User userReal=userService.queryUserByEmail(user.getUserEmail());
        if (userReal==null){
            userReal=userService.queryUserByPhone(user.getUserPhone());
        }
        userReal.setIdcard(user.getIdcard());
        userReal.setUserNickname(user.getUserNickname());
        userReal.setUserEmail(user.getUserEmail());
        userReal.setUserPhone(user.getUserPhone());
        userReal.setUserHeadimg(user.getUserHeadimg());
        userReal.setUserPrivacy(user.getUserPrivacy());
        userReal.setRealName(user.getRealName());
        userReal.setSex(user.getSex());
        userReal.setUserQq(user.getUserQq());
        userReal.setUserWechat(user.getUserWechat());
        userReal.setUserLocation(user.getUserLocation());
        Coach coachReal=coachService.findCoachByUserId(userReal.getUserId());
        if (coachReal==null){
            return ResultUtil.errorOperation("系统错误，请联系管理员");
        }
        coachReal.setCoachStyle(coach.getCoachStyle());
        coachReal.setAuthentication(coach.getAuthentication());
        coachReal.setCoachStatus(coach.getCoachStatus());
        coachReal.setFullClass(coach.getFullClass());
        coachReal.setCoachType(coach.getCoachType());
        coachReal.setStartTime(coach.getStartTime());
        coachReal.setCoachDetail(coach.getCoachDetail());
        coachReal.setExpectedSalary(coach.getExpectedSalary());
        session.setAttribute(SysConstant.CURRENT_USER,userReal);
        session.setAttribute(SysConstant.CURRENT_COACH,coachReal);
        Map<String,Object> map=new HashMap<>();
        map.put(SysConstant.CURRENT_COACH,coachReal);
        map.put(SysConstant.CURRENT_USER,userReal);
        return ResultUtil.actionSuccess("成功完善信息",map);
    }

    /**
     * 方法实现说明  展现个人隐私
     * @author      lxy
     * @Param:      user
     * @return      json对象 Result
     * @exception
     * @date        2019/4/27 21:07
     */
    @RequestMapping("/showPrivacy")
    @ResponseBody
    public Result showPrivacy(@RequestBody User user, HttpSession session){
        User userSession=(User)session.getAttribute(SysConstant.CURRENT_USER);
        if (userSession == null){
            return ResultUtil.errorOperation("展现信息失败");
        }
        if (userSession.getUserPhone()==null){
            user=userService.queryUserByEmail(userSession.getUserEmail());
            return ResultUtil.actionSuccess("展现信息成功",user);
        }
        user=userService.queryUserByPhone(userSession.getUserPhone());
        return ResultUtil.actionSuccess("展现信息成功",user);
    }
    /**
     * 方法实现说明 修改隐私
     * @author      lxy
     * @Param:      user
     * @return      json对象 Result
     * @exception
     * @date        2019/4/27 21:11
     */
    @RequestMapping("/updatePrivacy")
    @ResponseBody
    public Result updatePrivacy(@RequestBody User user,HttpSession session){
        User userSession= (User) session.getAttribute(SysConstant.CURRENT_USER);
        User userReal=userService.queryUserByEmail(userSession.getUserEmail());
        if (userReal==null){
            userReal=userService.queryUserByPhone(userSession.getUserPhone());
        }
        userReal.setUserPrivacy(user.getUserPrivacy());
        if(!userReal.getUserPrivacy().equals(user.getUserPrivacy())){
            return ResultUtil.errorOperation("修改失败");
        }
        return ResultUtil.actionSuccess("修改成功",userReal);
    }
    /**
     * 方法实现说明 展现账户与安全
     * @author      lxy
     * @Param:      user
     * @return      json对象 Result
     * @exception
     * @date        2019/4/29 10:56
     */
    @RequestMapping("/showAccount")
    @ResponseBody
    public Result showAccount(User user, HttpSession session){
        User userSession=(User)session.getAttribute(SysConstant.CURRENT_USER);
        if (userSession == null){
            return ResultUtil.errorOperation("展现信息失败");
        }
        if (userSession.getUserPhone()==null){
            user=userService.queryUserByEmail(userSession.getUserEmail());
            return ResultUtil.actionSuccess("展现信息成功",user);
        }
        user=userService.queryUserByPhone(userSession.getUserPhone());
        return ResultUtil.actionSuccess("展现信息成功",user);
    }
    /**
     * 方法实现说明 展现设置密码
     * @author      lxy
     * @Param:      user
     * @return      json对象 Result
     * @exception
     * @date        2019/4/29 10:58
     */
    @RequestMapping("/showModifyUserPwd")
    @ResponseBody
    public Result showModifyUserPwd(User user, HttpSession session){
        User userSession=(User)session.getAttribute(SysConstant.CURRENT_USER);
        if (userSession == null){
            return ResultUtil.errorOperation("展现信息失败");
        }
        if (userSession.getUserPhone()==null){
            user=userService.queryUserByEmail(userSession.getUserEmail());
            return ResultUtil.actionSuccess("展现信息成功",user);
        }
        user=userService.queryUserByPhone(userSession.getUserPhone());
        return ResultUtil.actionSuccess("展现信息成功",user);
    }



    /**
     * 方法实现说明 修改密码
     * @author      lxy
     * @Param:      confirmPwd，userNewPwd，userOldPwd
     * @return      json对象 Result
     * @exception
     * @date        2019/4/29 10:57
     */
    @RequestMapping("/modifyUserPwd")
    @ResponseBody
    public Result modifyUserPwd(User user,HttpSession session,@RequestBody String confirmPwd,
                                @RequestBody String userNewPwd,@RequestBody String userOldPwd){
        User userSession= (User) session.getAttribute(SysConstant.CURRENT_USER);
        User userReal=userService.queryUserByEmail(userSession.getUserEmail());
        if (userReal==null){
            userReal=userService.queryUserByPhone(userSession.getUserPhone());
        }
        SimpleHash userHashOldPwd = new SimpleHash("MD5",userOldPwd,userReal.getSalt(),2);
        if (!userHashOldPwd.toString().equals(userReal.getUserPwd())){
            return  ResultUtil.errorOperation("输入的旧密码不正确，请重新输入");
        }
        if (userOldPwd.equals(userNewPwd)){
            return ResultUtil.errorOperation("输入的新密码与旧密码不能相同，请重新输入");
        }
        if (!userNewPwd.equals(confirmPwd)){
            return ResultUtil.errorOperation("输入的新密码与确认密码不同，请重新输入");
        }
        userReal.setSalt(CodeUtil.userNumber());
        SimpleHash userHashNewPwd = new SimpleHash("MD5",userNewPwd,userReal.getSalt(),2);
        userReal.setUserPwd(userHashNewPwd.toString());
        if (!userReal.getUserPwd().equals(userHashNewPwd.toString())){
            return ResultUtil.connectDatabaseFail();
        }
        return ResultUtil.actionSuccess("修改成功",userReal);
    }




}