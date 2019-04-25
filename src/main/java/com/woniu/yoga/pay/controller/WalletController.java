package com.woniu.yoga.pay.controller;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.woniu.yoga.commom.utils.Attributes;
import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.manage.pojo.Coupon;
import com.woniu.yoga.pay.alipayConfig.AlipayConfig;
import com.woniu.yoga.pay.pojo.Wallet;
import com.woniu.yoga.pay.pojo.WalletRecord;
import com.woniu.yoga.pay.service.WalletService;
import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;


@Controller
@RequestMapping("/wallet")
public class WalletController {
    Map<String,Object> map = new HashMap<>();
    @Autowired
    private WalletService walletService;
    @Autowired
    private UserService userService;


/**
     * 根据user_id查找钱包
     *
     * @param userid
     * @return Wallet
     */

    @RequestMapping("/selectwallet")
    @ResponseBody
    public Result findWalletByUserId(int userid) {
        Wallet wallet =walletService.findWalletByUserId(userid);
        if (wallet!=null){
            return Result.success("我的钱包",wallet) ;
        }
        return Result.error("该用户没有钱包") ;
    }

    /**
     *更新钱包余额，用于余额的减少
     * @param walletId
     * @param money
     * @return
     */
    @RequestMapping("/updateusermoney")
    @ResponseBody
    public Result updateUserMoneyByWalletId(int walletId, BigDecimal money){
          if ((walletService.findWalletByWalletId(walletId).getBalance().compareTo(money))<0){
              return  Result.error("余额不足");
          }
        walletService.updateUserMoneyByWalletId(walletId,money);
        return Result.success("操作成功");
    }

    /**
     * 添加银行卡
     * @param userid
     * @param pwd
     * @param againPwd
     * @param bankcard
     * @return
     */
    @RequestMapping("addBankcard")
    @ResponseBody
    public Result addBankcardByUserId(Integer userid, String pwd, String againPwd, String bankcard){
        if (!pwd.equals(againPwd)){
            return Result.error("两次密码不匹配");
        }
        int n= walletService.addBankcardByUserId(userid,pwd,againPwd,bankcard);
        if (n>0){
            return Result.success("添加银行卡成功");
        }
        return Result.error("添加失败，请联系客服");
    }

    /**
     * 充值余额，用于余额的增加
     * @param walletId
     * @return 返回余额
     */
    @RequestMapping("/saveMoney")
    @ResponseBody
    public Result saveMoney(int walletId, BigDecimal money){

        if (walletService.saveMoney(walletId,money)>0){
            return Result.success("充值成功",walletService.findWalletByWalletId(walletId).getBalance());
        }
        return Result.error("充值失败");
    }

/**
     * 查询交易记录
     * @param userid
     * @return
     */

    @RequestMapping("/selectorder")
    @ResponseBody
    public Result selectOrderByUserId(int userid ) {
        List<WalletRecord> walletRecords =walletService.selectOrderByUserId(userid);
        if (walletRecords!=null){
            return Result.success("交易记录",walletRecords);
        }
     return Result.error("没有交易记录") ;
    }

/**
     * 查询优惠券
     * @param userid
     * @return
     */

    @RequestMapping("/selectcoupon")
    @ResponseBody
    public List<Coupon> selectUserCouponByUserId(int userid ) {
        return null;
    }


    /**
     *付款操作
     * @param allmoney
     * @param goodsIds
     * @param goodscount
     * @param request
     * @param goodsprice
     * @param response
     * @return
     * @throws AlipayApiException
     */
    @RequestMapping("/alipay")
    @ResponseBody
    public String alipay(String allmoney, String goodsIds, String goodscount, HttpServletRequest request, String goodsprice, HttpServletResponse response) throws  AlipayApiException {
        System.out.println("获取前端参数数据=="+goodsIds+"=====数量==="+goodscount);

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        //付款金额，必填
        String total_amount = allmoney;
        //订单名称，必填
        String subject = "商品";
        //商品描述，可空
        String body = "不错呦";

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        return result;
    }



    /**
     *  回调
     * @param request
     * @param response
     * @return
     * @throws AlipayApiException
     */
    //返回的订单数据
    @RequestMapping("/success")
    public String success(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {

        Map<String,String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
         //   System.out.println(name);
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {

                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
           // System.out.println(valueStr);
            params.put(name, valueStr);
            map.put(name, valueStr);

        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
        System.out.println(signVerified);
        int result =walletService.UpdateUserMoneyAndCreateRecord(map,request);
        if(!signVerified){
            return "payerror";//本地支付失败页面
        }
        if (result>0){
            map = null;
        }
        return "success";//本地支付成功页面

    }

}
