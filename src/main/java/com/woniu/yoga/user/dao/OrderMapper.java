package com.woniu.yoga.user.dao;


import com.woniu.yoga.user.pojo.Order;
import com.woniu.yoga.user.vo.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    /*
     * @Author liufeng
     * @Description //根据用户id和订单状态查询订单
     **/
    @Select("select * from order where order_status = #{orderStatus} and payer_id =#{userId}")
    List<Order> findOrderByUserIdAndStatus(Integer userId, String orderStatus);
    /*
     * @Author liufeng
     * @Description //根据订单编号更新订单状态
     **/
    @Update("update order set order_status = #{orderStatus} where order_id =#{orderId}")
    int updateStatusByOrderId(String orderId,Integer orderStatus);
}