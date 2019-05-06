package com.woniu.yoga.pay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.woniu.yoga.commom.vo.Result;
import com.woniu.yoga.manage.pojo.Coupon;
import com.woniu.yoga.pay.alipayConfig.AlipayConfig;
import com.woniu.yoga.pay.pojo.Wallet;
import com.woniu.yoga.pay.pojo.WalletRecord;
import com.woniu.yoga.pay.service.WalletRecordService;
import com.woniu.yoga.pay.service.WalletService;
import com.woniu.yoga.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserService userService;

    @Autowired
    private WalletRecordService walletRecordService;


    /**
     * 描述： 根据user_id查找钱包
     * URl： wallet/selectwallet
     * 请求方式： get
     * 参数： @param userId
     * 返回值：@return Result
     */

    @RequestMapping(value = "/selectwallet",method = RequestMethod.POST)
    @ResponseBody
    public Result findWalletByuserId(@RequestBody int userId) {
        Wallet wallet = walletService.findWalletByuserId(userId);
        if (wallet != null) {
            return Result.success("我的钱包", wallet);
        }
        return Result.error("该用户没有钱包");
    }

    /**
     * 描述：更新钱包余额，用于余额的减少
     * URL：URl： wallet/updateusermoney
     * 请求方式：post
     * 参数：@param walletId
     * @param money
     * 返回值：Result
     */
    @RequestMapping(value = "/updateusermoney",method = RequestMethod.POST)
    @ResponseBody
    public Result updateUserMoneyByWalletId(@RequestBody int walletId,@RequestBody BigDecimal money) {
        if ((walletService.findWalletByWalletId(walletId).getBalance().compareTo(money)) < 0) {
            return Result.error("余额不足");
        }
        walletService.updateUserMoneyByWalletId(walletId, money);
        return Result.success("操作成功",walletService.findWalletByWalletId(walletId).getBalance());
    }

    /**
     * 描述：提现操作,生成订单
     *URl:wallet/withdrawDeposit
     * 请求方式：post
     * 参数：@param userId
     * @param account
     * @param money
     * @param pwd
     * 返回值：Result
     */
    @RequestMapping(value = "/withdrawDeposit",method = RequestMethod.POST)
    @ResponseBody
    public Result withdrawDeposit(@RequestBody Integer userId,@RequestBody String account,@RequestBody BigDecimal money,@RequestBody String pwd) {
        Wallet wallet = walletService.findWalletByuserId(userId);
        if (!(wallet.getPayPwd().toString()).equals(pwd)) {
            return Result.error("密码错误，请重新输入密码");
        }
        if (wallet.getBalance().compareTo(money) < 0) {
            return Result.error("余额不足，请重新输入金额");
        }

        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        request.setBizContent("{" +
                "\"out_biz_no\":\"" + out_trade_no + "\"," +
                "\"payee_type\":\"ALIPAY_LOGONID\"," +
                "\"payee_account\":\"" + account + "\"," +
                "\"amount\":\"" + money + "\"," +
                "\"payer_show_name\":\"沙箱环境\"," +
                "\"payee_real_name\":\"沙箱环境\"," +
                "\"remark\":\"转账备注\"" +
                "  }");
        AlipayFundTransToaccountTransferResponse response = null;
        int n = -1;
        try {
            response = alipayClient.execute(request);
            n = walletService.updateUserMoneyByWalletId(wallet.getWalletId(), money);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return Result.error("请检查用户名是否正确！请重新操作");
        }
        if (response.isSuccess() && n > 0) {
            //插入订单
            WalletRecord walletRecord = new WalletRecord();
            walletRecord.setFromId(Integer.MAX_VALUE);
            walletRecord.setMoney(money);
            walletRecord.setPayType(0);
            walletRecord.setRecordType(2);
            walletRecord.setToId(userId);
            walletRecordService.insertRecord(walletRecord);
            System.out.println("调用成功");
            return Result.success("提现成功！提现金额为:" + money + "");
        } else {
            System.out.println("调用失败");
            return Result.error("提现提现失败！请重新操作");
        }
    }

    /**
     * 添加银行卡
     *
     * @param userId
     * @param pwd
     * @param againPwd
     * @param bankcard
     * @return
     */
    @RequestMapping("addBankcard")
    @ResponseBody
    public Result addBankcardByuserId(Integer userId, String pwd, String againPwd, String bankcard) {
        if (!pwd.equals(againPwd)) {
            return Result.error("两次密码不匹配");
        }
        int n = walletService.addBankcardByuserId(userId, pwd, againPwd, bankcard);
        if (n > 0) {
            return Result.success("添加银行卡成功");
        }
        return Result.error("系统异常");
    }

    /**
     * 描述：充值余额，用于余额的增加
     *URL：wallet/saveMoney
     * 请求方式：post
     * 参数：@param walletId
     * @param money
     * @return Result
     */
    @RequestMapping(value = "/saveMoney",method = RequestMethod.POST)
    @ResponseBody
    public Result saveMoney(@RequestBody int walletId,@RequestBody BigDecimal money) {

        if (walletService.saveMoney(walletId, money) > 0) {
            return Result.success("充值成功", walletService.findWalletByWalletId(walletId).getBalance());
        }
        return Result.error("充值失败");
    }

    /**
     * 描述：查询交易记录
     *  URl：wallet/selectorder
     * 请求方式：get
     * 参数：@param userId
     * @return Result
     */
    @RequestMapping(value = "/selectorder",method = RequestMethod.POST)
    @ResponseBody
    public Result selectOrderByuserId(@RequestBody int userId) {
        List<WalletRecord> walletRecords = walletService.selectOrderByuserId(userId);
        if (walletRecords.size()==0) {
            return Result.success("交易记录", walletRecords);
        }
        return Result.error("没有交易记录");
    }

    /**
     * 描述：查询优惠券
     *URL：wallet/selectcoupon
     * 请求方式：get
     * 参数：@param userId
     * @return Result
     */

    @RequestMapping(value = "/selectcoupon",method = RequestMethod.POST)
    @ResponseBody
    public Result selectUserCouponByuserId(@RequestBody int userId) {
        System.out.println(userId);
      List<Coupon> coupons = userService.selectCouponByUserId(userId);
        if (coupons.size()==0){
            return Result.error("亲，您还没有优惠券");
        }
        return Result.success("",coupons);
    }


    /**
     * 描述：付款操作
     *URl：wallet/alipay
     * 请求方式：post
     * @return String
     *
     */

    @RequestMapping("/alipay")
    @ResponseBody
    public String alipay(@RequestBody String allmoney, HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
        System.out.println(allmoney);
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
        System.out.println(result);
        return result;
    }

    /**
     * 描述：支付宝回调
     *
     * @return string
     *
     */
    @RequestMapping("/success")
    @ResponseBody
    public String success(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
        System.out.println("-----------回调-------------");
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }

            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
        int n=walletService.UpdateUserMoneyAndCreateRecord(params,request);
        System.out.println("n="+n);
        if (!signVerified){
            return "error" ;
        }
        if (n>0){
            params=null;
        }
        return "success";
    }

    /**
     * 描述：银联支付
     * URL：wallet/Unionpaypay
     *(使用银联支付需要在本地放入证书文件)
     * 请求方式：post
     * 参数：@param money
     * 返回值为空
     */
    @RequestMapping("/Unionpaypay")
    public void Unionpaypay (@RequestBody String money, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        String pay = walletService.Unionpaypay(money, req, resp);
        PrintWriter out = resp.getWriter();
        out.println(pay);
        out.close();
    }

}
