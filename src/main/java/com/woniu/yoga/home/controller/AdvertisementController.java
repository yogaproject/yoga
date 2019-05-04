package com.woniu.yoga.home.controller;

import com.woniu.yoga.commom.utils.JsonUtil;
import com.woniu.yoga.home.pojo.Advertisement;
import com.woniu.yoga.home.service.AdvertisementService;
import com.woniu.yoga.home.vo.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author huijie yan
 * @version 1.0.0
 * @description 广告接口类
 * @date 2019/4/22 12:15
 */
@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @ApiOperation(value = "展示广告")
    @PostMapping("/showAdvertisement")
    public Result<List<Advertisement>> showAdvertisement(){
        return advertisementService.showAdvertisement();
    }
}
