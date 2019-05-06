package com.woniu.yoga.home.service.impl;

import com.woniu.yoga.home.dao.AdvertisementMapper;
import com.woniu.yoga.home.pojo.Advertisement;
import com.woniu.yoga.home.service.AdvertisementService;
import com.woniu.yoga.home.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huijie yan
 * @version 1.0.0
 * @description 广告业务层
 * @date 2019/4/29 17:40
 */
@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Override
    public Result<List<Advertisement>> showAdvertisement() {
        return Result.success("成功",advertisementMapper.queryAdvertisement());
    }
}
