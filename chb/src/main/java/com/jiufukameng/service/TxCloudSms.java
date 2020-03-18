package com.jiufukameng.service;

import javax.servlet.http.HttpServletRequest;

import com.jiufukameng.entity.SmsParams;

public interface TxCloudSms {
	
	 public String sendSms(HttpServletRequest request,SmsParams smsParams);
	 

	 
}
