package com.woniu.yoga.manage.manage.controller;


import com.woniu.yoga.manage.manage.service.ManageMapperImp;
import com.woniu.yoga.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manage")
public class manageController {
    @Autowired
    ManageMapperImp service;
    @RequestMapping("/shows")
    @ResponseBody
    public List<User> showUserofStudent(){
        System.out.println(".......");
        List<User> users= service.showUserOfStudent();
        System.out.println(users);
        return users ;
    }

    @RequestMapping("/deletes")
    @ResponseBody
    public String deleteUserofStudent(Integer dsid){
        // System.out.println("delete");
        //System.out.println(dsid);
        int flag=service.deleteUserOfStudent(dsid);
        System.out.println(flag);
        if (flag==1)
            return "success" ;
        else
            return "false";
    }

    @RequestMapping("/showc")
    @ResponseBody
    public List<User> showUserofCoach(){
        System.out.println("showc");
        List<User> users= service.showUserOfCoach();
        System.out.println(users);
        return users ;
    }

    @RequestMapping("/deletec")
    @ResponseBody
    public String deleteUserofCoach(Integer dcid){
        // System.out.println("delete");
    System.out.println(dcid);
        int flag=service.deleteUserOfCoach(dcid);
        System.out.println(flag);
        if (flag==1)
            return "success" ;
        else
            return "false";
    }



}
