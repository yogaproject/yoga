package com.woniu.yoga.manage.manage.maction;

import com.woniu.yoga.manage.manage.service.CancelOrderImpl;
import com.woniu.yoga.user.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CancelOrder {
    @Autowired
    CancelOrderImpl service;

    public String cancelOrderAcion(String orderid){
        Order order=service.selectAimOrder(orderid);
     Integer  acceptid =order.getAccepterId();
     Integer   payerid =order.getPayerId();
     BigDecimal money =order.getOrderMoney();
        if (order==null){
            return "没有找到这个订单";
        }else {
            if (acceptid==null&&payerid==null&&money==null){   //判断字段有无丢失
               return "订单内没有信息或者信息丢失";
            }else{   //排除没有记录和信息丢失
                  int backstudent =service.updateBackToPay(payerid,money);  //返还学员支出
                  int redececoach =service.updateReduceAccepter(acceptid,money);//扣除教练收支
                      if (backstudent==1&&redececoach==1) {
                           int walletrecordflag=service.insertWalletrecoredCancelRecord(payerid,acceptid,money);                                                   //添加钱包流水
                            int changepaystatue=service.updateOrderPaystatue(orderid);
                                             if (walletrecordflag==1&&changepaystatue==1){
                                                 return "操作成功";
                                             }else{
                                                 return "添加记录或修改订单状态失败";
                                             }

                      }else{
                         return "返还金额操作失败";
                      }
            }

        }

    }

}
