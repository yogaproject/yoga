package com.woniu.yoga.pay.alipayConfig;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092600598716";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCXkHZgpv1lW5+QzEc0u8VnxdI3woukKHzoTWULTdAY3vwMqLlPN73uAYiyXG7HOmh2Hyq6cH/XwZysoHUQ4Bw2Y8freATmIYhC12Q6c6d6vNi5tVQxuMkANGzDV4BAiFuBIiejiOUQwCCkHw6JWinEXs/lXBtntHIOFwgoZ1+FS4+ZjAhez0sbcuFuwL+PrRWnygwsb36KHQhnRLfdzLDjZ9taKhn6BpllQFoQDz+WYrrollSNCFHwfK09ZH4nKcLfrdGWixYfIZXi2UPijtf1eaTrxsTuVoEnneMoYAsAKgiU3nndRfj6cJhWclg6XdiVNDN/gm7VPlGRLg9kqtNHAgMBAAECggEAN/DVEHVyH4AC1BHh0j/f655jKbt/iPZ8q80tF1AAF73tr8pyox+C1Z7xUku1g4k2VyrHvYqaVQscBccvjiv5ncWUW3+v5zTHM0yGrOBVF4MuRtT7n2KBpbaz0iGwr6DkjTQLB1wWl+kh4xGnbuC9e8I234PwhYVfd33ev/LvmaWn9HTdX9Ub2CnU4r0Mhzgm8sAWP8OkPaULS+wz8cXmrFmXmTmaCOARDttUjLCN0uiiXXYWoEyhL0/QvLXYFbcFNRX9twYABHFZarN+58BQuxwcAlVrEPLZTZ7M746oZXeH20AwAolFZ0otx+6qLCbYJR7KpGNpj8Wnr90/n/rryQKBgQDkl2iq2Gdec/Cwn5rPwd2wSalJv9RjXK87lppuye728SwMtVZS0wGCmcoRhfND2NxpiH4qgbg4iga0i9a3ktpCSBKMOcVAfBx4MKwgSYbrCt3mRvWkUIde6iTCw2L5/DrykPcdEkYl243oLMLQwpfwxhg3R5aB2f4f6wkPI26c2wKBgQCpvLalB3bnO5gGBn00OOf6sFa+uL5T0P273x3gv1luGn1hy9s/PIDBgSxwcDtjpHrM4bmG+OUruHiTDm1JfTd1NBQwAU3cjbjzBgSxmik537WRFqg9Y3w+FHN60Ipi1W58D9zJdye+4SAqeR6OxbVfB9DAOvKlisTXJ/XTcU85BQKBgQCaG/WFkKWHyRRQjxmYzTN/APovbTNr1XWR78Onw13mf8tZufs7lWPkP3g6fhr198MJJusgDxo6i9tfZxNX6ZpZqytN/Diec83hvbadgdSCq23ULKy4nU3WUHZ+E6mrrT+0fsUKhWJjbXRrPZoNkdNLkNGMmye93VB1BkiyOwn8lwKBgGCmysHyHCzTbF+R9l10QfQn0V1MyWPkz3bZN6QwkXX2s4qqkC/T/eUx7uIfplG46QwdRI2s0KIyqsXiIy0Dlj6w5wXm2DVMphIMTUPsaqk/z53MbwR1z9txvcgjU4mvyvft+GTn6sPwYWaODW5y+jIguyea6WyGNkvlvz8leF0ZAoGBANvd5jo+G5Qo5P2hs1qyjmem2H2o/TB1Neh5fNnP/I2DAI3GHWES6GEiFAMxzP537NnEDyO/JUCon/0tEqopwKSnvzbL/KEeA0r2ybm9Qw61NS+vOpo2FzAFlmtJMmAB/oW3Nve9jEAO0bKLvW2V9AGgTEsOWp3AVmZlGNQilzvI";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArjuSs9nwc08usGChtO2Wq99UYNdQywtZR0q96KaBMzedi4uvP5n1jryEp8yM32dbHnsB4Ik7lxuxCbuH8cyrnSV39oCE7IjZa8bnX0AWj1W9Sho7LgCkxkyXV9gLYzMXoMrTA/iZfLfOHBlSH3fbEbuDOSQUujI7L43efv6jWa84iyW93fEarAvojJnjJxEeb/jXWZ3OUKWSTkuIKKSixqUFuFnCCTpE/EC1BhjwPQXoRPqxxWnRgAeoyTQR69v6fSG2oDlnB8SDeoJGbiR8aK3aPt+794jGB0rlB+IhoND5E5GsgQVt4unbj5aan8SjpHzD13rqvJEwTh88+fkpbwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://twfiwy.natappfree.cc/wallet/success1";
	//public static String notify_url = "http://localhost/wallet/success";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://twfiwy.natappfree.cc/wallet/success";
	//public static String return_url = "http://localhost/wallet/success";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "D:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

