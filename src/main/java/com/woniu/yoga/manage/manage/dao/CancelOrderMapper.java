package com.woniu.yoga.manage.manage.dao;

import com.woniu.yoga.user.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CancelOrderMapper {
    @Select("select * from order where order_id=#{0} ")
    Order selectAimOrder(String orderid);
    @Update("update wallet set balance=balance+#{1} where user_id=#{0}")
    int updateBackToPay(Integer payerid, BigDecimal money);
    @Update("update wallet set balance=balance-#{1} where user_id=#{0}")
    int updateReduceAccepter(Integer acceptid, BigDecimal money);
    @Insert("insert wallet_record values(default,#{1},#{0},1,#{2},4)")
    int insertWalletrecoredCancelRecord(Integer payerid, Integer acceptid, BigDecimal money);
    @Update("update order set pay_status=2 where order_id=#{0}")
    int updateOrderPaystatue(String orderid);
}
