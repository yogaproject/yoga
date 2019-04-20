package com.woniu.yoga.venue.dao;

<<<<<<< HEAD
import com.woniu.yoga.venue.pojo.Coach;
import com.woniu.yoga.venue.pojo.Venue;

import java.math.BigDecimal;
import java.util.List;

=======
import com.woniu.yoga.venue.pojo.Venue;

>>>>>>> dev
public interface VenueMapper {
    int deleteByPrimaryKey(Integer venueId);

    int insert(Venue record);

    int insertSelective(Venue record);

    Venue selectByPrimaryKey(Integer venueId);

    int updateByPrimaryKeySelective(Venue record);

    int updateByPrimaryKey(Venue record);
<<<<<<< HEAD

    List<Coach> queryCoachByVagueConditions(Coach coach, BigDecimal up_expected_salary,BigDecimal down_expected_salary);
=======
>>>>>>> dev
}