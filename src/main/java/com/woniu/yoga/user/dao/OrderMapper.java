package com.woniu.yoga.user.dao;


import com.woniu.yoga.user.dto.OrderDTO;
import com.woniu.yoga.user.pojo.Order;
import com.woniu.yoga.user.util.UserMapperProviderUtil;
import com.woniu.yoga.user.vo.Result;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(String orderId) throws SQLException;

    int insert(Order record) throws SQLException;

    int insertSelective(Order record) throws SQLException;

    Order selectByPrimaryKey(String orderId) throws SQLException;

    int updateByPrimaryKeySelective(Order record) throws SQLException;

    int updateByPrimaryKey(Order record) throws SQLException;

    /*
     * @Author liufeng
     * @Description //根据用户id和订单状态查询订单
     **/
    List<OrderDTO> findStudentOrder(Integer userId, String status) throws SQLException;
    List<OrderDTO> findCoachOrder(Integer userId, String status) throws SQLException;
    /*
     * @Author liufeng
     * @Description //根据订单编号更新订单状态
     **/
    @Update("update `order` set order_status = #{orderStatus} where order_id =#{orderId} and order_flag = 0")
    int updateStatusByOrderId(String orderId, Integer orderStatus) throws SQLException;

}