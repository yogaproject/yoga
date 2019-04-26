package com.woniu.yoga.crowdfunding.service;

import com.woniu.yoga.crowdfunding.pojo.CrowdFunding;
import com.woniu.yoga.crowdfunding.pojo.Supporter;

import java.util.ArrayList;
import java.util.List;

public interface CrowdFundingService {

    /**
     * @Description 查找一个项目
     * @Author HanFeng
     * @Create in 2019/4/20 16:53
     * @param cfId
     * @return
     * @Version v2.0
     */
    CrowdFunding selCfById(Integer cfId);

    /**
     * @Description 更改项目状态：0待审核状态，1进行中状态，2完成状态
     * @Author HanFeng
     * @Create in 2019/4/22 10:05
     * @param
     * @return
     * @Version v1.0
     */
    int updateStatus(CrowdFunding crowdFunding);

    /**
     * @Description 更新项目
     * @Author HanFeng
     * @Create in 2019/4/22 10:08
     * @param
     * @return
     * @Version v1.0
     */
    int updateCf(CrowdFunding crowdFunding);

    /**
     * @Description 查询符合条件的项目并排序
     * @Author HanFeng
     * @Create in 2019/4/20 16:52
     * @param crowdFunding
     * @param orderCondition
     * @param orderType
     * @return
     * @Version v2.0
     */
    ArrayList<CrowdFunding> getCfs(CrowdFunding crowdFunding, String orderCondition, String orderType);

    /**
     * @Description 更新项目当前募集资金额
     * @Author HanFeng
     * @Create in 2019/4/20 16:50
     * @param supporter
     * @return
     * @Version v1.0
     */
    int updateCurMoney(Supporter supporter);

    /**
     * @Description 支持者数量增加1
     * @Author HanFeng
     * @Create in 2019/4/22 10:03
     * @return
     * @Version v1.0
     */
    int updateSupCount(Supporter supporter);

    /**
     * @Description 查询个人关注的所有项目
     * @Author HanFeng
     * @Create in 2019/4/23 12:01
     * @param userId
     * @return
     * @Version v2.0
     */
    List<CrowdFunding> getMyFoc(Integer userId);

    /**
     * @Description 新增关注记录
     * @Author HanFeng
     * @Create in 2019/4/23 14:04
     * @param userId
     * @param cfId
     * @return
     * @Version v1.0
     */
    int insertFoc(Integer userId, Integer cfId);

    /**
     * @Description 修改关注状态
     * @Author HanFeng
     * @Create in 2019/4/23 14:04
     * @param userId
     * @param cfId
     * @return
     * @Version v1.0
     */
    int modifyFoc(Integer userId, Integer cfId);

    /**
     * @Description 发起一个众筹项目
     * @Author HanFeng
     * @Create in 2019/4/23 14:44
     * @param crowdFunding
     * @return
     * @Version v1.0
     */
    int insertCf(CrowdFunding crowdFunding);
}
