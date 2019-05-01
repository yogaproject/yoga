package com.woniu.yoga.user.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;

import java.io.IOException;

/**
 * @Description: java类作用描述PhoneUtil
 * @Author: lxy
 * @time: 2019/4/20 1:36
 */
public class PhoneUtil {
    private int templateId;

    public PhoneUtil(int templateId) {
        this.templateId = templateId;
    }

    //    templateId = 317053;密码模板
    //    templateId = 316608;验证码模板

    private static int appid = 1400201990; // 短信应用SDK，AppID1400开头
    private static String appkey = "66d459841a36482e11579a0b7a3bbe95";// 短信应用SDK AppKey
    private static String smsSign = "路边生活"; // 另外签名参数使用的是`签名内容`，而不是`签名ID`
    private static SmsSingleSender ssender;
    public void sendPhoneMessage(String userPhone, String userVerifyCode) {
        try {
            String[] params = {userVerifyCode, "3"};//数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
            ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", userPhone,
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
        } catch (HTTPException e) {
            e.printStackTrace();// HTTP响应码错误
        } catch (JSONException e) {
            e.printStackTrace();// json解析错误
        } catch (IOException e) {
            e.printStackTrace();  // 网络IO错误
        }
    }

}

