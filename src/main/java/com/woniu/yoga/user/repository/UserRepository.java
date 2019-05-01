package com.woniu.yoga.user.repository;

import com.woniu.yoga.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: java类作用描述
 * @Author: 路边
 * @time: 2019/4/16 19:44
 */
@Transactional
public interface UserRepository extends JpaRepository<User,Integer> {


    //查看邮箱是否存在
    @Query("select u from User u where u.userEmail=?1 and u.userFlag=0")
    User queryUserByEmail(String userEmail);
    //查看手机是否存在
    @Query("select u from User u where u.userPhone=?1 and u.userFlag=0")
    User queryUserByPhone(String userPhone);

}
