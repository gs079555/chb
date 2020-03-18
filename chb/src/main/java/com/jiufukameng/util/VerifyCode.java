package com.jiufukameng.util;

import java.util.Random;

/**
 *  动态生成短信验证码工具类
 */
public class VerifyCode {

	/**
	 * 创建指定数量的随机字符串
	 * @param isNumber 是否是数字
	 * @param length
	 * @return
	 */

	
	public static String createRandom(){
		String resultStr = "";
		Random rand = new Random();
		for (int i=0;i<6;i++){
			resultStr+=rand.nextInt(10);
		}
		return resultStr;
		
	}
	

}
