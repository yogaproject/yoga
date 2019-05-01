package com.woniu.yoga.user.util;

/*
 * 正则表达式工具类，主要记录一些常用的正则表达式
 * @author lu
 */
public class RegexpUtil {
	//手机号
	public final static String RegExp_PHONE="^(13|14|15|17|18|19)\\d{9}$";
	//身份证
	public final static String RegExp_ID="^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
	//固定电话号码
	public final static String LandLine_Num="0//d{2,3}-\\d{7,8}";
	//出生年月日
	public final static String BrithDate="[1-9]\\d{3}-((0[1-9])|(10|11|12))-(([0-2][1-9])|10|20|30|31)";
	//只支持中文  [\u4e00-\u9fa5]
	public final static String Chinese="^[\u4e00-\u9fa5]+$";
	//邮箱
	public final static String RegExp_Mail="^[a-zA-Z\\d]{1}+[\\w{5,17}]+@[\\w{2,3}]+\\.[a-zA-Z]{2,3}$";
	//以字母开头，长度在6~18之间，只能包含字母、数字和下划线；
	public final static String RegExp_PASS="^[a-zA-Z]\\w{5,17}$";



	
}
