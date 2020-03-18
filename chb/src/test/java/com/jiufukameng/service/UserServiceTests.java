package com.jiufukameng.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jiufukameng.entity.SmsParams;
import com.jiufukameng.entity.User;
import com.jiufukameng.service.ex.ServiceException;
import com.jiufukameng.util.VerifyCode;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
	@Autowired
	IUserService service;
	
	@Autowired
	TxCloudSms txCloudSms;
	

	
	
	@Test
	public void reg() {
		try {
//			User user = new User();
//			user.setUserpass("1234");
//			user.setMobile("18764513333");
//			user.setUserlogin("18764513335");
//			service.reg(user);
			System.err.println("OK..");
		} catch (Exception e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
		
	}
	
	@Test
	public void login() {
		try {
			String userlogin = "18764513330";
			String userpass = "1234";
//			User user = service.login(userlogin, userpass);
//			System.out.println(user);
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void changePassword() {
		try {
			String userlogin = "18764513335";
			String oldPassword = "1234";
			String newPassword = "123456";
//			service.changePassword(userlogin, oldPassword, newPassword);
			System.err.println("OK");
			
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	
	
	
	

	
	
	@Test
	public void sms() {
		 // 生成随机的六位数验证码
		VerifyCode verifyCode = new VerifyCode();
		String[] params = {VerifyCode.createRandom()};
//		String params = verifyCode.createRandom();
		
		
//        SmsParams smsParams = new SmsParams();
        System.out.println("生成的验证码为：" + params);
		
//		String str = txCloudSms.sendSms(smsParams);
//		System.err.println(str);
		
	}
	
	
	@Test
	public void getByid() {
		Integer userlogin=1;
		User user = service.getByid(userlogin);
		System.out.println(user);
	}
	
	@Test
	public void changeInfo() {
		try {
			
			User user = new User();
			user.setUserlogin("18764513330");
			user.setRealName("超级管理员");
			user.setMobile("17705302050");
			service.changeInfo(user);
			System.out.println("OK...");
		} catch (Exception e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
}
