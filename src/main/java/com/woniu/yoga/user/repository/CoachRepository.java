package com.woniu.yoga.user.repository;

import com.woniu.yoga.user.pojo.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: java类作用描述CoachRepository
 * @Author: lxy
 * @time: 2019/4/25 17:28
 */
@Transactional
public interface CoachRepository extends JpaRepository<Coach,Integer> {
    Coach save(Coach coach);
    Coach findCoachByUserId(Integer userId);
}
