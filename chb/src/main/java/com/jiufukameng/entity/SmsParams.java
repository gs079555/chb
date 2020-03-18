package com.jiufukameng.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 短信验证需要的手机号和验证码
 * @author Administrator
 *
 */
@Data
@Accessors(chain = true)
public class SmsParams {
	/**
	 * 验证码
	 */
	private String verifyCode;
	/**
	 * 手机号码
	 */
	private String phone;
	
	
	public String getVerifyCode() {
		return verifyCode;
	}
	public String getPhone() {
		return phone;
	}
	
	
	
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public SmsParams(String phone,String params) {
		super();
		this.verifyCode = params;
		this.phone = phone;
	}

	
	

	
	
	
}
