package com.woniu.yoga.crowdfunding.controller;

import com.woniu.yoga.crowdfunding.pojo.CrowdFunding;
import com.woniu.yoga.crowdfunding.service.CrowdFundingService;
import com.woniu.yoga.commom.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * 众筹controller
 */
@RestController
@RequestMapping("/cf")
public class CrowdFundingController {

    @Autowired
    private CrowdFundingService crowdFundingService;


    /**
     * 获取某一个众筹项目
     * @param cfId
     * @return
     */
    @RequestMapping("/getOneCf")
    public Result getOneCf(Integer cfId){
        CrowdFunding cf = crowdFundingService.selCfById(cfId);
        return  Result.success("成功", cf);

    }

    /**
     * 控制项目上架与下架
     * @param crowdFunding
     * @return
     */
    @RequestMapping("/operate")
    public Result operate(CrowdFunding crowdFunding){
        crowdFundingService.updateStatus(crowdFunding);
        if (crowdFunding.getCfStatus() == 1){
            return  Result.success("上架操作成功");
        }
        if (crowdFunding.getCfStatus() == 0){
            return  Result.success("下架操作成功");
        }
        return Result.error("网络繁忙，请稍后再试");
    }

    /**
     * 项目修改
     * @param crowdFunding
     * @return
     */
    @RequestMapping("/modify")
    public Result modify(CrowdFunding crowdFunding){
        crowdFundingService.updateCf(crowdFunding);
        return  Result.success("项目更新成功");
    }

    /**
     * 通过条件控制获取对应项目
     * @param crowdFunding
     * @param orderCondition
     * @param orderType
     * @return
     */
    @RequestMapping("/list")
    public Result list(CrowdFunding crowdFunding,
                       @RequestParam(value="orderCondition",required = false, defaultValue = "cf_sup_count")String orderCondition,
                       @RequestParam(value = "orderType",required = false,defaultValue = "desc")String orderType){
        ArrayList<CrowdFunding> cfs = crowdFundingService.getCfs(crowdFunding,orderCondition,orderType);
        return Result.success("项目展示", cfs);

    }

    /**
     * 加精控制
     * @param crowdFunding
     * @return
     */
    @RequestMapping("/setLight")
    public Result setLight(CrowdFunding crowdFunding){
        crowdFundingService.updateCf(crowdFunding);
        if (crowdFunding.getCfLight() == 0){
            return Result.success("已取消加精");
        }
        if (crowdFunding.getCfLight() == 1){
            return Result.success("加精成功");
        }
        return Result.error("网络繁忙，请稍后再试");
    }

}
