package com.woniu.yoga.home.controller;

import com.woniu.yoga.commom.utils.JsonUtil;
import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.communicate.constant.SysConstant;
import com.woniu.yoga.home.pojo.Homepage;
import com.woniu.yoga.home.service.HomepageService;
import com.woniu.yoga.user.pojo.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpSession;

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
    * @param latitude 传入经纬度
    * @param currentPage 查询当前页
    * @param longitude 传入经纬度
    * @param pageSize 每页多少条数据
    * @author huijie yan
    * @date 2019/4/22
    * @return com.woniu.yoga.commom.vo.Result
    */
    @ApiOperation(value = "展示附近用户发布的消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "latitude", value = "纬度", required = true, paramType = "path"),
            @ApiImplicitParam(name = "longitude", value = "经度", required = true, paramType = "path"),
            @ApiImplicitParam(name = "currentPage", value = "当前页",required = true, paramType = "path"),
            @ApiImplicitParam(name = "currentPage", value = "每页查询数据条数",required = true, paramType = "path")
    })
    @GetMapping("/{latitude}/{longitude}/{currentPage}/{pageSize}/showHomepage")
    public String showHomepage(@PathVariable("latitude") Float latitude, @PathVariable("longitude") Float longitude,
                               @PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize){

        return JsonUtil.toJson(homepageService.selectHomepages(latitude,longitude, currentPage, pageSize));
    }

    /**
    * @Description 根据动态内容id获取详细信息
    * @param mid
    * @author huijie yan
    * @date 2019/4/22
    * @return com.woniu.yoga.commom.vo.Result
    */
    @ApiOperation(value = "根据动态内容id获取图文详情")
    @ApiImplicitParam(name = "mid", value = "动态内容id", required = true, paramType = "path")
    @GetMapping("/{mid}/showHomepageDetail")
    public String showHomepageDetail(@PathVariable("mid") Integer mid){

        return JsonUtil.toJson(homepageService.showHomepageDetail(mid));
    }

    /**
    * @Description 发布新动态
    * @param homepage
    * @author huijie yan
    * @date 2019/4/24
    * @return com.woniu.yoga.commom.vo.Result
    */
    @ApiOperation(value = "新增动态")
    @ApiImplicitParam(name = "homepage", value = "动态", required = true, paramType = "path")
    @PostMapping("/{homepage}/pushHomepage")
    public String pushHomepage(@PathVariable("homepage") Homepage homepage, HttpSession session){
        User user = (User)session.getAttribute(SysConstant.CURRENT_USER);
        if (user == null){
            return JsonUtil.toJson(Result.error("未登录"));
        }
        return JsonUtil.toJson(homepageService.pushHomepage(homepage, user.getUserId()));
    }

    /**
    * @Description 根据roleId决定查询附近场馆动态还是教练动态
    * @param roleId
    * @param latitude
    * @param longitude
    * @param currentPage
    * @param pageSize
    * @author huijie yan
    * @date 2019/4/26
    * @return java.lang.String
    */
    @ApiOperation(value = "展示附近教练或者场馆发布的动态内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "path"),
            @ApiImplicitParam(name = "latitude", value = "纬度", required = true, paramType = "path"),
            @ApiImplicitParam(name = "longitude", value = "经度", required = true, paramType = "path"),
            @ApiImplicitParam(name = "currentPage", value = "当前页",required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页查询数据条数",required = true, paramType = "path")
    })
    @GetMapping(value = "/{roleId}/{latitude}/{longitude}/{currentPage}/{pageSize}/showOtherHomepage")
    public String showOtherHomepage(@PathVariable("roleId") Integer roleId, @PathVariable("latitude") Float latitude, @PathVariable("longitude") Float longitude,
                                    @PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize){

        return JsonUtil.toJson(homepageService.showOtherHomepage(roleId,latitude, longitude, currentPage, pageSize));
    }

    /**
    * @Description 删除动态
    * @param mid
    * @author huijie yan
    * @date 2019/4/26
    * @return java.lang.String
    */
    @ApiOperation(value = "删除动态")
    @ApiImplicitParam(name = "mid", value = "动态id", required = true, paramType = "path")
    @DeleteMapping("/{mid}/deleteHomepage")
    public String deleteHomepage(@PathVariable("mid") Integer mid){
        return JsonUtil.toJson(homepageService.deleteHomepage(mid));
    }
}
