package com.woniu.yoga.manage.manage.service;

import com.woniu.yoga.manage.manage.dao.ManageMapper;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.venue.pojo.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageMapperImp implements ManageMapper {
    @Autowired
    ManageMapper dao;

    @Override
    public List<Venue> showVenue() {
        return dao.showVenue();
    }

    @Override
    public int deleteVenue(Integer venueid) {
        return 0;
    }

    @Override
    public int addVenue(Venue venue) {
        return 0;
    }

    @Override
    public List<User> showUserOfCoach() {
        return dao.showUserOfCoach();
    }

    @Override
    public int deleteUserOfCoach(Integer dcid) {
        return dao.deleteUserOfCoach(dcid);
    }

    @Override
    public List<User> showUserOfStudent() {
        return dao.showUserOfStudent();
    }

    @Override
    public int deleteUserOfStudent(Integer dsid) {
        return dao.deleteUserOfStudent(dsid);
    }

    @Override
    public int changeUserOfStudent() {
        return 0;
    }
}
