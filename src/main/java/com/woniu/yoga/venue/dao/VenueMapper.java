package com.woniu.yoga.venue.dao;

import com.woniu.yoga.user.pojo.Coach;
import com.woniu.yoga.venue.pojo.Recruitment;
import com.woniu.yoga.venue.pojo.Venue;

import java.math.BigDecimal;
import java.util.List;

public interface VenueMapper {
    int deleteByPrimaryKey(Integer venueId);

    int insert(Venue record);

    int insertSelective(Venue record);

    Venue selectByPrimaryKey(Integer venueId);

    int updateByPrimaryKeySelective(Venue record);

    int updateByPrimaryKey(Venue record);

    //场馆模糊查询符合条件的教练
    List<Coach> queryCoachByVagueConditions(Coach coach, BigDecimal up_expected_salary,BigDecimal down_expected_salary);

    //场馆点击申请签约按钮,生成“等待用户同意”的数据
    int waitForSign(Integer venueId,Integer coachId);

    //教练确认签约，改变签约状态为“1”
    int coachSignMapper(int cv_id);

    //教练拒绝签约，改变签约状态为“2”
    int coachRefuseMapper(int cv_id);

    //场馆发布招聘信息
    int venueAddRecruitMapper(Recruitment recruitment);
}