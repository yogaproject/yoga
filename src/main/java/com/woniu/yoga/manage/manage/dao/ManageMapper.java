package com.woniu.yoga.manage.manage.dao;

import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.venue.pojo.Venue;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ManageMapper {
//场馆操作
    @Select("select * from venue")
public List<Venue> showVenue();   //展示场馆
    @Delete("delete from venue where venue_id=#{0}")
public int deleteVenue(Integer venueid);          //删除场馆
    @Insert("insert from ")
public int addVenue(Venue venue);      //添加场馆

 //教练操作

 @Select("select * from user where role_id=2 and user_flag=0")
 public List<User> showUserOfCoach();
    @Update("update user set user_flag=1 where user_id=#{0}")
    public int deleteUserOfCoach(Integer dcid);



//学员操作
//展示学员
@Select("select * from user where role_id=1 and user_flag=0 ")
public List<User> showUserOfStudent();
//删除学员
    @Update("update user set user_flag=1 where user_id=#{0}")
public int deleteUserOfStudent(Integer dsid);
//修改学员
public int changeUserOfStudent();

}
