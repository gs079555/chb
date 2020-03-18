package com.jiufukameng.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.jiufukameng.entity.SmsParams;
import com.jiufukameng.service.TxCloudSms;
import com.jiufukameng.util.VerifyCode;

@Component
public class TxCloudSmsImpl implements TxCloudSms {

	// 短信应用SDK AppId
	@Value("${tx.sms.appId}")
	int appId; // 1400开头

	// 短信应用SDK AppKey
	@Value("${tx.sms.appKey}")
	String appKey;

	// 短信模版ID，需要在短信应用中申请
	@Value("${tx.sms.templateId}")
	int templateId; // NOTE:真实的模版ID需要在短信控制台中申请

	// 签名
	@Value("${tx.sms.smsSign}")
	String smsSign; // 签名参数使用的是‘签名内容’，而不是‘签名ID’

	@Value("${tx.sms.smsEffectiveTime}")
	String smsEffectiveTime;

	/**
	 * 指定模版ID单发短信
	 */
	@Override
	public String sendSms(HttpServletRequest request, SmsParams smsParams) {
		String rep = "error";
		try {

			// 数组具体的元素个数和模板中变量个数必须一致，例如示例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
			String[] params = { VerifyCode.createRandom() };
			List<String> str = Arrays.asList(params);
			System.err.println("str:==" + str);

			// 为用户创建一个session对象,有则使用,无则创建
			HttpSession session = request.getSession();
			String userlogin = smsParams.getPhone();
			

			// TODO 基于参数str进行加密，得到加密后的密码
			String md5VerifyCode = getMd5VerifyCode(str.get(0));
			System.err.println("验证码==" + str.get(0));
			// TODO 将加密后的密码和盐值封装到session中
			System.err.println("reg()>>md5password=" + md5VerifyCode);
			 session.setAttribute("user", userlogin);
			 session.setAttribute("code", md5VerifyCode);
			//验证码在session中的存活时间为5分钟
			session.setMaxInactiveInterval(5*60);
			System.err.println("userlogin::session::"+session.getAttribute("user"));
			SmsSingleSender smsSingleSender = new SmsSingleSender(appId, appKey);
			System.err.println("smsParams.getVerifyCode()" + smsParams.getVerifyCode());
			Object code = session.getAttribute("code");
			System.out.println("session::code::" + code);
			// 签名参数未提供或者为空时，会使用默认签名发送短信
			SmsSingleSenderResult smsSingleSenderResult = smsSingleSender.sendWithParam("86", smsParams.getPhone(),
					templateId, params, smsSign, "", "");
			System.err.println(smsSingleSenderResult);
			// 如果返回码不是0，说明配置有错，返回错误信息

			if (smsSingleSenderResult.result == 0) {
				rep = "success";
			} else {
				rep = smsSingleSenderResult.errMsg;
			}

		} catch (HTTPException e) {
			// HTTP响应码错误
			e.printStackTrace();
		} catch (JSONException e) {
			// json解析错误
			e.printStackTrace();
		} catch (IOException e) {
			// 网络IO错误
			e.printStackTrace();
		} catch (Exception e) {
			// 网络IO错误
			e.printStackTrace();
		}
		return rep;
	}

	



	/**
	 * 对验证码进行加密
	 * 
	 * @param code 原始验证码
	 * @return 加密后的验证码
	 */
	public String getMd5VerifyCode(String code) {
		// 规则：对password+salt进行3重加密
		String str = code ;
		for (int i = 0; i < 3; i++) {
			str = DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
		}
		return str;
	}



}
