package com.woniu.yoga.home.controller;

import com.woniu.yoga.communicate.constant.SysConstant;
import com.woniu.yoga.home.pojo.Homepage;
import com.woniu.yoga.home.service.HomepageService;
import com.woniu.yoga.home.vo.Address;
import com.woniu.yoga.home.vo.HomepageVo;
import com.woniu.yoga.home.vo.Result;
import com.woniu.yoga.user.pojo.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

/**
 * @author huijie yan
 * @version 1.0.0
 * @description 主页图文类
 * @date 2019/4/22 9:48
 */
@RestController
@RequestMapping("/homepage")
public class HomepageController {

    @Autowired
    private HomepageService homepageService;

    /**
    * @Description 展示附近人发布的消息
//    * @param latitude 传入经纬度
//    * @param currentPage 查询当前页
//    * @param longitude 传入经纬度
    * @param pageSize 每页多少条数据
    * @author huijie yan
    * @date 2019/4/22
    * @return com.woniu.yoga.commom.vo.Result
    */
    @ApiOperation(value = "展示附近用户发布的消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "latitude", value = "纬度", required = true, paramType = "form",dataType = "Float"),
            @ApiImplicitParam(name = "longitude", value = "经度", required = true, paramType = "form", dataType = "Float")
    })
    @PostMapping("/showHomepage")
    public Result<List<HomepageVo>> showHomepage( @RequestBody Address address){
        return homepageService.selectHomepages(address.getLatitude(),address.getLongitude());
    }

    /**
    * @Description 根据动态内容id获取详细信息
    * @param mid
    * @author huijie yan
    * @date 2019/4/22
    * @return com.woniu.yoga.commom.vo.Result
    */
    @ApiOperation(value = "根据动态内容id获取图文详情")
    @ApiImplicitParam(name = "mid", value = "动态内容id", required = true, paramType = "body")
    @PostMapping("/showHomepageDetail")
    public Result<HomepageVo> showHomepageDetail(@RequestBody Integer mid){
        return homepageService.showHomepageDetail(mid);
    }

    /**
    * @Description 发布新动态
    * @param homepage
    * @author huijie yan
    * @date 2019/4/24
    * @return com.woniu.yoga.commom.vo.Result
    */
    @ApiOperation(value = "新增动态")
    @ApiImplicitParam(name = "homepage", value = "动态", required = true, paramType = "body")
    @ApiResponse(code = 500, message = "未登录")
    @PostMapping("/pushHomepage")
    public Result pushHomepage(@RequestBody Homepage homepage, HttpSession session){
        User user = (User)session.getAttribute(SysConstant.CURRENT_USER);
        if ("".equals(homepage.getContent()) || homepage.getContent() == null){
            return Result.error("内容不能为空");
        }
        if ("".equals(homepage.getTitle()) || homepage.getTitle() == null){
            return Result.error("标题不能为空");
        }
        if (user == null){
            return Result.error("未登录");
        }
        return homepageService.pushHomepage(homepage, user.getUserId());
    }

    /**
    * @Description 根据roleId决定查询附近场馆动态还是教练动态
    * @param address
    * @author huijie yan
    * @date 2019/5/5
    * @return com.woniu.yoga.home.vo.Result<java.util.List<com.woniu.yoga.home.vo.HomepageVo>>
    */
    @ApiOperation(value = "展示附近教练或者场馆发布的动态内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "body"),
            @ApiImplicitParam(name = "latitude", value = "纬度", required = true, paramType = "body"),
            @ApiImplicitParam(name = "longitude", value = "经度", required = true, paramType = "body"),
    })
    @PostMapping(value = "/showOtherHomepage")
    public Result<List<HomepageVo>> showOtherHomepage(@RequestBody Address address){
        return homepageService.showOtherHomepage(address.getRoleId(),address.getLatitude(), address.getLongitude());
    }

    /**
    * @Description 删除动态
    * @param mid
    * @author huijie yan
    * @date 2019/4/26
    * @return java.lang.String
    */
    @ApiOperation(value = "删除动态")
    @ApiImplicitParam(name = "mid", value = "动态id", required = true, paramType = "body")
    @ApiResponse(code = 200,message = "删除成功")
    @DeleteMapping("/deleteHomepage")
    public Result deleteHomepage(@RequestBody Integer mid){
        return homepageService.deleteHomepage(mid);
    }
}
