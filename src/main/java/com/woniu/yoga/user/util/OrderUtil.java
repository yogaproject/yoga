package com.woniu.yoga.user.util;

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
            return NEWORDER;
        }
        if (status.equals("教练已确认")) {
            return STARTORDER;
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
}
