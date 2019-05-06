package com.woniu.yoga.venue.repository;

import com.woniu.yoga.venue.pojo.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: java类作用描述VenueRepository
 * @Author: lxy
 * @time: 2019/4/25 17:45
 */
@Transactional
public interface VenueRepository extends JpaRepository<Venue,Integer> {
    Venue save(Venue venue);
}
