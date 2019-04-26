package com.woniu.yoga.crowdfunding.controller;

import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.crowdfunding.pojo.CrowdFunding;
import com.woniu.yoga.crowdfunding.pojo.Journal;
import com.woniu.yoga.crowdfunding.pojo.Supporter;
import com.woniu.yoga.crowdfunding.service.CrowdFundingService;
import com.woniu.yoga.crowdfunding.service.JournalService;
import com.woniu.yoga.crowdfunding.service.SupporterService;
import com.woniu.yoga.crowdfunding.utils.LogicUtils;
import com.woniu.yoga.crowdfunding.vo.MySupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project yoga
 * @Description 众筹controller
 * @Author HanFeng
 * @Create in 2019/4/20 16:27
 */
@RestController
@RequestMapping("/cf")
public class CrowdFundingController {

    @Autowired
    private CrowdFundingService crowdFundingService;
    @Autowired
    private SupporterService supporterService;
    @Autowired
    private JournalService journalService;

    /**
     * @Description 获取某一个众筹项目
     * @Author HanFeng
     * @Create in 2019/4/20 16:27
     * @param cfId
     * @return
     * @Version v2.0
     */
    @RequestMapping("/getOneCf")
    public Result getOneCf(Integer cfId){
        CrowdFunding crowdFunding = crowdFundingService.selCfById(cfId);
        return  Result.success("成功", crowdFunding);

    }


    /**
     * @Description 控制项目上架与下架(PC)
     * @Author HanFeng
     * @Create in 2019/4/20 16:28
     * @param crowdFunding
     * @return
     * @Version v2.0
     */
    @RequestMapping("/operate")
    public Result operate(CrowdFunding crowdFunding){
        int num = crowdFundingService.updateStatus(crowdFunding);
        if (num == 1){
            if (crowdFunding.getCfStatus() == 1){
                return  Result.success("上架操作成功");
            }
            if (crowdFunding.getCfStatus() == 0){
                return  Result.success("下架操作成功");
            }
        }
        return Result.error("网络繁忙，请稍后再试");
    }

    /**
     * @Description 项目修改(PC)
     * @Author HanFeng
     * @Create in 2019/4/20 16:30
     * @param crowdFunding
     * @return
     * @Version v2.0
     */
    @RequestMapping("/modify")
    public Result modify(CrowdFunding crowdFunding){
        int num = crowdFundingService.updateCf(crowdFunding);
        if (num == 1){
            return  Result.success("项目更新成功");
        }
        return Result.error("项目更新失败，请稍后再试");
    }


    /**
     * @Description 通过条件控制获取对应项目
     * @Author HanFeng
     * @Create in 2019/4/20 16:31
     * @param crowdFunding
     * @param orderCondition
     * @param orderType
     * @return
     * @Version v2.0
     */
    @RequestMapping("/list")
    public Result list(CrowdFunding crowdFunding,
                       @RequestParam(value="orderCondition",required = false, defaultValue = "cf_sup_count")String orderCondition,
                       @RequestParam(value = "orderType",required = false,defaultValue = "desc")String orderType){
        ArrayList<CrowdFunding> crowdFundings = crowdFundingService.getCfs(crowdFunding,orderCondition,orderType);
        return Result.success("项目展示", crowdFundings);

    }


    /**
     * @Description 加精控制(PC)
     * @Author HanFeng
     * @Create in 2019/4/20 16:31
     * @param crowdFunding
     * @return
     * @Version v2.0
     */
    @RequestMapping("/setLight")
    public Result setLight(CrowdFunding crowdFunding){
        crowdFunding.setCfLight(1);
        crowdFunding.setCfId(2);
        int num = crowdFundingService.updateCf(crowdFunding);
        if (num == 1) {
            if (crowdFunding.getCfLight() == 0) {
                return Result.success("已取消加精");
            }
            if (crowdFunding.getCfLight() == 1) {
                return Result.success("加精成功");
            }
        }
        return Result.error("网络繁忙，请稍后再试");
    }


    /**
     * @Description 会员参与众筹（APP） 暂缺会员账户向平台账户转款的接口、新参与者信息传递给项目发起人消息接口、参与者积分增加接口
     * @Author HanFeng
     * @Create in 2019/4/20 16:32
     * @param supporter
     * @return
     * @Version v1.0
     */
    @RequestMapping("/join")
    public Result join(Supporter supporter){
        BigDecimal b = new BigDecimal(1200);
        Supporter ss = new Supporter(null, 2, 1008, b, "很好哦", null,0);
        try {
           new LogicUtils().forJoin(ss,crowdFundingService,supporterService);
            return Result.success("支持项目成功，感谢您的支持！");
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }

    }

    /**
     * @Description 获取某项目的所有日报,以日期降序排列（APP）
     * @Author HanFeng
     * @Create in 2019/4/23 10:08
     * @param cfId
     * @return
     * @Version v2.0
     */
    @RequestMapping("/journals")
    public Result journals(Integer cfId){
        cfId = 2;
        List<Journal> journals = journalService.getJournals(cfId);
        return Result.success("当前所有日报", journals);
    }


    /**
     * @Description 新增日报，适用于项目发起者更新日报信息（APP）
     * @Author HanFeng
     * @Create in 2019/4/23 10:25
     * @param journal
     * @return
     * @Version v2.0
     */
    @RequestMapping("/keepJournal")
    public Result keepJournal(Journal journal){
        int num = journalService.saveJournal(journal);
        if (num != 1){
            return Result.error("日报更新失败，请稍后再试");
        }
        return  Result.success("日报已更新");
    }


    /**
     * @Description 获取个人参与的所有项目信息以及其对每个项目投入的总资金，按资金降序排列（APP）
     * @Author HanFeng
     * @Create in 2019/4/23 11:50
     * @param userId
     * @return
     * @Version v2.0
     */
    @RequestMapping("/mySup")
    public Result mySup(Integer userId){
        List<MySupVO> mySupVOS = new LogicUtils().forMySup(userId, crowdFundingService, supporterService);
        if (mySupVOS == null){
            return Result.success("您还未参与众筹项目");
        }
        return Result.success("这是你参与的所有众筹项目", mySupVOS);
    }

    /**
     * @Description 获取关注的所有项目信息，以关注时间降序排列（APP）
     * @Author HanFeng
     * @Create in 2019/4/23 12:14
     * @param userId
     * @return
     * @Version v2.0
     */
    @RequestMapping("/myFoc")
    public Result myFoc(Integer userId){
        userId = 1008;
        List<CrowdFunding> crowdFundings = crowdFundingService.getMyFoc(userId);
        if (crowdFundings == null){
            return Result.success("您还未关注任何众筹项目");
        }
        return Result.success("您关注的所有项目",crowdFundings);
    }

    /**
     * @Description 关注/取关（APP）
     * @Author HanFeng
     * @Create in 2019/4/23 14:18
     * @param userId
     * @param cfId
     * @param focStatus
     * @return
     * @Version v2.0
     */
    @RequestMapping("/modifyFoc")
    public Result modifyFoc(Integer userId, Integer cfId, @RequestParam(value ="focStatus") Integer focStatus){
        new LogicUtils().forModifyFoc(userId,cfId,focStatus,crowdFundingService);
        if (focStatus == 0){
            return Result.success("已关注");
        }
        return Result.success("已取消关注");

    }

    /**
     * @Description 新建众筹项目（APP）   缺 message ： 在创建成功后要向管理员发送请求审核的消息，携带此项目的信息
     * @Author HanFeng
     * @Create in 2019/4/23 14:23
     * @param
     * @return
     * @Version v2.0
     */
    @RequestMapping("/initiate")
    public Result initiate(CrowdFunding crowdFunding){
        /*crowdFunding.setCfDescribe("lalal");
        crowdFunding.setCfImg("jjjjjj");
        User user = new User();
        user.setUserId(1008);
        crowdFunding.setCfSponsor(user);
        BigDecimal bigDecimal = new BigDecimal(500000);
        crowdFunding.setCfTargetMoney(bigDecimal);
        crowdFunding.setCfTitle("天天向上");*/
        int num = crowdFundingService.insertCf(crowdFunding);
        if (num != 1){
            return Result.error("网络异常，请稍后再试");
        }
        return Result.success("您已提交一个众筹项目申请，请等候平台审核信息");
    }
}
