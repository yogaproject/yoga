package com.woniu.yoga.crowdfunding.utils;

import com.woniu.yoga.commom.exception.YogaException;
import com.woniu.yoga.crowdfunding.pojo.CrowdFunding;
import com.woniu.yoga.crowdfunding.pojo.Supporter;
import com.woniu.yoga.crowdfunding.service.CrowdFundingService;
import com.woniu.yoga.crowdfunding.service.SupporterService;
import com.woniu.yoga.crowdfunding.vo.MySupVO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project yoga
 * @Description 业务逻辑辅助
 * @Author HanFeng
 * @Create in 2019/4/20 16:35
 */
@Component
public class LogicUtils {


    /**
     * @Description 为参与项目功能做流程逻辑控制，
     * @Author HanFeng
     * @Create in 2019/4/20 16:36
     * @param
     * @return
     * @Version v1.0
     */
    public void forJoin( Supporter supporter , CrowdFundingService crowdFundingService, SupporterService supporterService){

        //1、项目募集金增加
        int num = crowdFundingService.updateCurMoney(supporter);
        if (num != 1){
            throw new YogaException(500, "支付异常,请稍后再试");
        }

        //2、会员向平台转入支付款


        //3、判断支持者集中是否已包含当前会员，如果包含，supporter表增加一条记录
        List<Supporter> supporters = supporterService.querySupByIds(supporter);
        if (supporters != null){
            int num1 = supporterService.insertSupporter(supporter);
            if (num1 != 1){
                throw new YogaException(500, "添加支持者信息异常,请稍后再试");
            }
        }else{

            //4、判断支持者集中是否已包含当前会员，如果没有包含，supporter表增加一条记录，支持者数量+1,
            int num2 = crowdFundingService.updateSupCount(supporter);
            if (num2 != 1){
                throw new YogaException(500, "增加支持者数量异常,请稍后再试");
            }
            int num3 = supporterService.insertSupporter(supporter);
            if (num3 != 1){
                throw new YogaException(500, "添加支持者信息异常,请稍后再试");
            }
        }

        //5、调用积分增加接口，支持者对应的账户积分增加

        //6、调用通知接口，向发起者发送信息


        //7、判断金额是否已经达标，如果达标更改项目状态
        CrowdFunding crowdFunding = crowdFundingService.selCfById(supporter.getCfId());
        BigDecimal money = crowdFunding.getCfTargetMoney().subtract(crowdFunding.getCfCurMoney());
        if (money.doubleValue() <= 0 && crowdFunding.getCfStatus() != 3){
            crowdFunding.setCfStatus(3);
            int num4 = crowdFundingService.updateStatus(crowdFunding);
            if (num4 != 1){
                throw new YogaException(500, "更改状态异常,请稍后再试");
            }
        }
    }


    /**
     * @Description 提供个人参与的所有项目信息及对每个项目的总投资金额
     * @Author HanFeng
     * @Create in 2019/4/23 11:19
     * @param
     * @param userId
     * @return
     * @Version v2.0
     */
    public List<MySupVO> forMySup(Integer userId, CrowdFundingService crowdFundingService, SupporterService supporterService){
        List<MySupVO> ll = supporterService.selMySup(userId);
        if (ll == null){
            return null;
        }
        List<MySupVO> mySupVOS = new ArrayList<MySupVO>();
        for(MySupVO mySupVO : ll){
            CrowdFunding crowdFunding = crowdFundingService.selCfById(mySupVO.getCfId());
            mySupVO.setCrowdFunding(crowdFunding);
            mySupVOS.add(mySupVO);
        }
        return mySupVOS;
    }


    /**
     * @Description 处理关注状态
     * @Author HanFeng
     * @Create in 2019/4/23 14:12
     * @param
     * @return
     * @Version v1.0
     */
    public void forModifyFoc(Integer userId, Integer cfId, Integer focStatus, CrowdFundingService crowdFundingService){
        if (focStatus == 0){
            int n1 = crowdFundingService.insertFoc(userId,cfId);
            if (n1 != 1){
                throw new YogaException(500, "新增关注异常");
            }
        }
        if (focStatus == 1){
            int n2 = crowdFundingService.modifyFoc(userId,cfId);
            if (n2 != 1){
                throw new YogaException(500, "取消关注异常");
            }
        }
    }
}
