package com.woniu.yoga.home.service;


import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.home.pojo.Homepage;

public interface HomepageService {
    Result selectHomepages(Float latitude, Float longitude, Integer currentPage, Integer pageSize);

    Result showHomepageDetail(Integer mid);

    Result pushHomepage(Homepage homepage);

    Result showOtherHomepage(Integer roleId, Float latitude, Float longitude, Integer currentPage, Integer pageSize);
}
