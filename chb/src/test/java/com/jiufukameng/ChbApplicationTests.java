package com.jiufukameng;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jiufukameng.entity.SmsParams;
import com.jiufukameng.service.TxCloudSms;

@SpringBootTest
class ChbApplicationTests {

	@Autowired
	DataSource dataSource;
	
	@Resource
	TxCloudSms txCloudSms;
	
	
	@Test
	public void getConnection() throws SQLException {
		Connection conn =  dataSource.getConnection();
		System.err.println(conn);
	}
	@Test
	public void testSms() {
		//生成随机的六位数验证码
		Random random = new Random(4);
		Integer verifyCode = random.nextInt(1000000);
//		SmsParams smsParams = new SmsParams("18764513330", verifyCode.toString());
		System.err.println("您的验证码是：("+verifyCode+")，不要随意告诉别人哦");
		
//		String str = txCloudSms.sendSms(smsParams);
//		System.err.println("jjjj="+str);
		

	}
	
	
	
}
