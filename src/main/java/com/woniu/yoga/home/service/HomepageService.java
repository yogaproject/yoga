package com.woniu.yoga.home.service;

import com.woniu.yoga.home.pojo.Homepage;
import com.woniu.yoga.home.vo.HomepageVo;
import com.woniu.yoga.home.vo.Result;

import java.util.List;

public interface HomepageService {
    Result selectHomepages(Float latitude, Float longitude, Integer currentPage, Integer pageSize);

    Result showHomepageDetail(Integer mid);

    Result pushHomepage(Homepage homepage, Integer userId);

    Result<List<HomepageVo>> showOtherHomepage(Integer roleId, Float latitude, Float longitude, Integer currentPage, Integer pageSize);

    Result deleteHomepage(Integer mid);
}
