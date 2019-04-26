package com.woniu.yoga.crowdfunding.service.Impl;

import com.woniu.yoga.crowdfunding.dao.SupporterMapper;
import com.woniu.yoga.crowdfunding.pojo.Supporter;
import com.woniu.yoga.crowdfunding.service.SupporterService;
import com.woniu.yoga.crowdfunding.vo.MySupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project yoga
 * @Description
 * @Author HanFeng
 * @Create in 2019/4/20 17:30
 */
@Service
public class SupporterServiceImpl implements SupporterService {

    @Autowired
    private SupporterMapper supporterMapper;

    @Override
    public List<Supporter> querySupByIds(Supporter supporter) {
        return supporterMapper.querySupByIds(supporter);
    }

    @Override
    public int insertSupporter(Supporter supporter) {
        return supporterMapper.insertSelective(supporter);
    }

    @Override
    public List<MySupVO> selMySup(Integer userId) {
        return supporterMapper.selMySup(userId);
    }
}
