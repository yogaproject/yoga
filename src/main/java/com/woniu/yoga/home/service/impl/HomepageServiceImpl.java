package com.woniu.yoga.home.service.impl;

import com.github.pagehelper.PageInfo;
import com.woniu.yoga.commom.utils.CommentUtil;
import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.home.dao.HomepageMapper;
import com.woniu.yoga.home.pojo.Homepage;
import com.woniu.yoga.home.service.HomepageService;
import com.woniu.yoga.home.vo.HomepageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huijie yan
 * @version 1.0.0
 * @description 图文显示业务处理类
 * @date 2019/4/22 10:00
 */
@Service
public class HomepageServiceImpl implements HomepageService {

    @Autowired
    private HomepageMapper homepageMapper;

    @Override
    public Result selectHomepages(Float latitude, Float longitude, Integer currentPage, Integer pageSize) {
        if (latitude == 0 || longitude == 0){
            return Result.error("未获取到地址，请重新定位");
        }
        List<HomepageVo> list = homepageMapper.queryHomepages(latitude, longitude, currentPage, pageSize);
        return dealWithList(list);
    }

    @Override
    public Result showHomepageDetail(Integer mid) {
        if (mid == 0){
            return Result.error("未获取到动态内容id:m_id");
        }
        HomepageVo vo = homepageMapper.selectHomepageDetail(mid);
        return Result.success("成功",vo);
    }

    @Override
    public Result pushHomepage(Homepage homepage) {
        if (homepage.getUserId() == 0){
            return Result.error("未获取到用户id");
        }
        int num = homepageMapper.insertSelective(homepage);
        if (num == 1){
            return Result.success("发布成功");
        }
        return Result.error("发布失败");
    }

    @Override
    public Result showOtherHomepage(Integer roleId, Float latitude, Float longitude, Integer currentPage, Integer pageSize) {

        List<HomepageVo> list = homepageMapper.queryOtherHomepages(roleId,latitude, longitude, currentPage, pageSize);
        return dealWithList(list);
    }

    private Result dealWithList(List<HomepageVo> list){
        PageInfo pageInfo = new PageInfo(list);
        for (HomepageVo vo: list) {
            String publishTime = CommentUtil.publishTime(vo.getCreateTime());
            vo.setPublishTime(publishTime);
        }
        return Result.success("成功",list);
    }
}
