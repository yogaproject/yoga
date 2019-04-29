package com.woniu.yoga.crowdfunding.service;

import com.woniu.yoga.crowdfunding.pojo.Supporter;
import com.woniu.yoga.crowdfunding.vo.MySupVO;

import java.util.List;

/**
 * @Project yoga
 * @Description 支持者接口
 * @Author HanFeng
 * @Create in 2019/4/20 17:26
 */
public interface SupporterService {

    /**
     * @Description 查询支持者对象
     * @Author HanFeng
     * @Create in 2019/4/20 17:29
     * @param supporter
     * @return
     * @Version v1.0
     */
    List<Supporter> querySupByIds(Supporter supporter);

    /**
     * @Description 添加一条支持者记录
     * @Author HanFeng
     * @Create in 2019/4/22 9:50
     * @param supporter
     * @return
     * @Version v1.0
     */
    int insertSupporter(Supporter supporter);

    /**
     * @Description 查询个人参与的项目ID及对应的投资总金额，按金额降序排列
     * @Author HanFeng
     * @Create in 2019/4/23 11:23
     * @param
     * @param userId
     * @return
     * @Version v1.0
     */
    List<MySupVO> selMySup(Integer userId);
}
