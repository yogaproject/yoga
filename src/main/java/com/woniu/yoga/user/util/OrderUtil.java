package com.woniu.yoga.user.util;

import com.woniu.yoga.commom.utils.OrderIdUtil;
import com.woniu.yoga.user.pojo.Order;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.Date;

/**
 * @Author liufeng
 * @ClassName OrderUtil
 * @Date 2019/4/22 15:28
 * @Version 1.0
 * @Description 将字符串转为整数类型的订单状态
 **/
public class OrderUtil {
    public static final int NEWORDER = 15;
    public static final int STARTORDER = 29;
    public static final int WAITTOPAY = 16;
    public static final int PAIED = 17;
    public static final int END = 33;
    public static final int CANCELED = 31;
    public static final int APPLICATIONFORDRAWBACK = 30;
    public static final int REFUNDSUCCESS = 18;
    public static final int REFUNDFAILURE = 32;

    public static int checkOrderStatus(String status) {
        if (status.equals("新下单")) {
            return NEWORDER;//15
        }
        if (status.equals("教练已确认")) {
            return STARTORDER;//29
        }
        if (status.equals("待付款")) {
            return WAITTOPAY;
        }
        if (status.equals("已付款") || status.equals("待评价")) {
            return PAIED;
        }
        if (status.equals("已完成")) {
            return END;
        }
        if (status.equals("已取消")) {
            return CANCELED;
        }
        if (status.equals("申请退款")) {
            return APPLICATIONFORDRAWBACK;
        }
        if (status.equals("退款成功")) {
            return REFUNDSUCCESS;
        }
        if (status.equals("退款失败")) {
            return REFUNDFAILURE;
        }
        return -1;
    }

    public static String checkStatus(Integer status) {
        if (status == NEWORDER) {
            return "待确认";//15
        }
        if (status == STARTORDER) {
            return ("教练已确认");//29
        }
        if (status == WAITTOPAY) {
            return ("待付款");
        }
        if (status == PAIED) {
            return ("待评价");
        }
        if (status == END) {
            return ("已完成");
        }
        if (status == CANCELED) {
            return ("已取消");
        }
        if (status == APPLICATIONFORDRAWBACK) {
            return ("申请退款");
        }
        if (status == REFUNDSUCCESS) {
            return ("退款成功");
        }
        if (status == REFUNDFAILURE) {
            return ("退款失败");
        }
        return "";
    }

    public static Order getRepeatOrder(Order order) {
        Order repeatOrder = new Order();
        BeanUtils.copyProperties(order, repeatOrder);
        repeatOrder.setOrderStatus(NEWORDER);
        repeatOrder.setCouponId(null);
        repeatOrder.setCreateTime(new Date());
        repeatOrder.setOrderId(OrderIdUtil.getOrderId());
        repeatOrder.setDiscount(null);
        return repeatOrder;
    }

    public static String getOrderStatus(String orderStatus) {
        int[] status = null;
        if (orderStatus.equals("未完成订单")) {
            status = new int[5];
            status[0] = NEWORDER;
            status[1] = STARTORDER;
            status[2] = WAITTOPAY;
            status[3] = PAIED;
            status[4] = APPLICATIONFORDRAWBACK;
        }
        if (orderStatus.equals("已完成订单")) {
            status = new int[4];
            status[0] = CANCELED;
            status[1] = END;
            status[2] = REFUNDFAILURE;
            status[3] = REFUNDSUCCESS;
        }
        return convertByArray(status);
    }

    public static String convertByArray(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        String s = "";
        for (int i = 0; i < array.length; i++) {
            if (i != array.length - 1) {
                s += array[i] + ",";
            } else {
                s += array[i];
            }
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(getOrderStatus("未完成订单"));
    }
}
