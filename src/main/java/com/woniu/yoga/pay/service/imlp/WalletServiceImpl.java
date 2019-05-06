package com.woniu.yoga.pay.service.imlp;

import com.woniu.yoga.pay.UnionpayConfig.sdk.AcpService;
import com.woniu.yoga.pay.UnionpayConfig.sdk.DemoBase;
import com.woniu.yoga.pay.UnionpayConfig.sdk.LogUtil;
import com.woniu.yoga.pay.UnionpayConfig.sdk.SDKConfig;
import com.woniu.yoga.pay.dao.WalletMapper;
import com.woniu.yoga.pay.pojo.Wallet;
import com.woniu.yoga.pay.pojo.WalletRecord;
import com.woniu.yoga.pay.service.WalletRecordService;
import com.woniu.yoga.pay.service.WalletService;

import com.woniu.yoga.user.pojo.User;
import com.woniu.yoga.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletMapper walletMapper;
    @Autowired
    private WalletRecordService walletRecordService;
    @Autowired
    private UserService userService;

    @Override
    public Wallet findWalletByuserId(int userId) {
        return walletMapper.findWalletByuserId(userId);
    }

    @Override
    public List<WalletRecord> selectOrderByuserId(int userId) {
        return walletMapper.selectOrderByuserId(userId);
    }

    @Override
    public int UpdateUserMoneyAndCreateRecord(Map<String, String> map, HttpServletRequest request) {
       // User user = (User) map.get(Attributes.CURRENT_USER);
        User user =new User();
        user.setUserId(21);//测试数据
        BigDecimal money = new BigDecimal(map.get("total_amount"));
        //根据用户id,找到对应钱包
        Wallet wallet = walletMapper.findWalletByuserId(user.getUserId());
        //修改钱包余额 充值
        int updatResult = walletMapper.updateUserMoneyByWalletId(wallet.getWalletId(), money);
        WalletRecord walletRecord = new WalletRecord();
        walletRecord.setFromId(user.getUserId());
        walletRecord.setMoney(money);
        walletRecord.setPayType(0);
        walletRecord.setRecordType(1);
        walletRecord.setToId(Integer.MAX_VALUE);
        walletRecord.setWalletId(wallet.getWalletId());
        int m = walletRecordService.insertRecord(walletRecord);
        System.out.println(updatResult);
        System.out.println(m);
        if (updatResult >= 0 && m > 0) {
            return 200;
        }
        return -1;
    }

    @Override
    public int updateUserMoneyByWalletId(int walletId, BigDecimal money) {
        return walletMapper.updateUserMoneyByWalletId(walletId, money);
    }

    @Override
    public int saveMoney(int walletId, BigDecimal money) {
        return walletMapper.saveMoney(walletId, money);
    }

    @Override
    public int addBankcardByuserId(Integer userId, String pwd, String againPwd, String bankcard) {
        return walletMapper.addBankcardByWalletId(userId, pwd, againPwd, bankcard);
    }

    @Override
    public Wallet findWalletByWalletId(int walletId) {
        return walletMapper.selectByPrimaryKey(walletId);
    }

    @Override
    public String Unionpaypay(String money, HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html; charset=" + DemoBase.encoding);

        //前台页面传过来的
//        String merId = req.getParameter("merId");
//        String txnAmt = req.getParameter("txnAmt");
//        String orderId = req.getParameter("orderId");
//        String txnTime = req.getParameter("txnTime");

        Map<String, String> requestData = new HashMap<String, String>();

        /***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
        requestData.put("version", DemoBase.version);                       //版本号，全渠道默认值
        requestData.put("encoding", DemoBase.encoding);                       //字符集编码，可以使用UTF-8,GBK两种方式
        requestData.put("signMethod", SDKConfig.getConfig().getSignMethod()); //签名方法
        requestData.put("txnType", "01");                                     //交易类型 ，01：消费
        requestData.put("txnSubType", "01");                              //交易子类型， 01：自助消费
        requestData.put("bizType", "000201");                              //业务类型，B2C网关支付，手机wap支付
        requestData.put("channelType", "08");                              //渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板  08：手机

        /***商户接入参数***/
        String orderId = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        requestData.put("merId", "777290058168976");                                     //商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号
        requestData.put("accessType", "0");                              //接入类型，0：直连商户
        requestData.put("orderId", orderId);             //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则
        requestData.put("txnTime", DemoBase.getCurrentTime());        //订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
        requestData.put("currencyCode", "156");                              //交易币种（境内商户一般是156 人民币）
        requestData.put("txnAmt", money);                                  //交易金额，单位分，不要带小数点
        //requestData.put("reqReserved", "透传字段");        		      //请求方保留域，如需使用请启用即可；透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节。出现&={}[]符号时可能导致查询接口应答报文解析失败，建议尽量只传字母数字并使用|分割，或者可以最外层做一次base64编码(base64编码之后出现的等号不会导致解析失败可以不用管)。

        requestData.put("riskRateInfo", "{commodityName=测试商品名称}");

        //前台通知地址 （需设置为外网能访问 http https均可），支付成功后的页面 点击“返回商户”按钮的时候将异步通知报文post到该地址
        //如果想要实现过几秒中自动跳转回商户页面权限，需联系银联业务申请开通自动返回商户权限
        //异步通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
        requestData.put("frontUrl", DemoBase.frontUrl);

        //后台通知地址（需设置为【外网】能访问 http https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，失败的交易银联不会发送后台通知
        //后台通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
        //注意:1.需设置为外网能访问，否则收不到通知    2.http https均可  3.收单后台通知后需要10秒内返回http200或302状态码
        //    4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200，那么银联会间隔一段时间再次发送。总共发送5次，每次的间隔时间为0,1,2,4分钟。
        //    5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
        requestData.put("backUrl", DemoBase.backUrl);

        // 订单超时时间。
        // 超过此时间后，除网银交易外，其他交易银联系统会拒绝受理，提示超时。 跳转银行网银交易如果超时后交易成功，会自动退款，大约5个工作日金额返还到持卡人账户。
        // 此时间建议取支付时的北京时间加15分钟。
        // 超过超时时间调查询接口应答origRespCode不是A6或者00的就可以判断为失败。
        requestData.put("payTimeout", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime() + 15 * 60 * 1000));

        //////////////////////////////////////////////////
        //
        //       报文中特殊用法请查看 PCwap网关跳转支付特殊用法.txt
        //
        //////////////////////////////////////////////////

        /**请求参数设置完毕，以下对请求参数进行签名并生成html表单，将表单写入浏览器跳转打开银联页面**/
        Map<String, String> submitFromData = AcpService.sign(requestData, DemoBase.encoding);  //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。

        String requestFrontUrl = SDKConfig.getConfig().getFrontRequestUrl();  //获取请求银联的前台地址：对应属性文件acp_sdk.properties文件中的acpsdk.frontTransUrl
        String html = AcpService.createAutoFormHtml(requestFrontUrl, submitFromData, DemoBase.encoding);   //生成自动跳转的Html表单

        LogUtil.writeLog("打印请求HTML，此为请求报文，为联调排查问题的依据：" + html);
        //将生成的html写到浏览器中完成自动跳转打开银联支付页面；这里调用signData之后，将html写到浏览器跳转到银联页面之前均不能对html中的表单项的名称和值进行修改，如果修改会导致验签不通过

        return html;

    }

    @Override
    public void saveWallet(Integer userId) {
        walletMapper.saveWallet(userId);
    }


}
