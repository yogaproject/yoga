package com.woniu.yoga.home.controller;

import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.home.pojo.Homepage;
import com.woniu.yoga.home.service.HomepageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("/showHomepage")
    public Result showHomepage(@RequestParam(value = "latitude", required = false,defaultValue = "30.575f")Float latitude, @RequestParam(value = "longitude", required = false, defaultValue = "104.058f")Float longitude,
                               @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage, @RequestParam(value = "pageSize", required = false, defaultValue = "10")Integer pageSize){
        return homepageService.selectHomepages(latitude,longitude, currentPage, pageSize);
    }

    /**
    * @Description 根据动态内容id获取详细信息
    * @param m_id
    * @author huijie yan
    * @date 2019/4/22
    * @return com.woniu.yoga.commom.vo.Result
    */
    @RequestMapping("/showHomepageDetail")
    public Result showHomepageDetail(Integer m_id){
        return homepageService.showHomepageDetail(m_id);
    }

    /**
    * @Description 发布新动态
    * @param homepage
    * @author huijie yan
    * @date 2019/4/24
    * @return com.woniu.yoga.commom.vo.Result
    */
    @RequestMapping("/pushHomepage")
    public Result pushHomepage(Homepage homepage){
        return homepageService.pushHomepage(homepage);
    }

    @RequestMapping("/showOtherHomepage")
    public Result showOtherHomepage(@RequestParam(value = "roleId", required = true) Integer roleId, @RequestParam(value = "latitude", required = false,defaultValue = "30.575f")Float latitude, @RequestParam(value = "longitude", required = false, defaultValue = "104.058f")Float longitude,
                                    @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage, @RequestParam(value = "pageSize", required = false, defaultValue = "10")Integer pageSize){
        return homepageService.showOtherHomepage(roleId,latitude, longitude, currentPage, pageSize);
    }
}
