package com.jiufukameng.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jiufukameng.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTests {
	@Autowired
	private UserMapper mapper;
	

	
	@Test
	public void updatePassword() {
		User user = new User();
		String userlogin="18764513330";
		String userpass = "123456";
		Integer rows = mapper.updatePassword(userlogin, userpass);
		
		if (rows==1) {
			System.err.println("修改成功");			
		}else {
			System.err.println("修改失败");
		}
	}
	
	
	
	
	@Test
	public void findById() {
		Integer id = 1;
		User user = mapper.findByid(id);
		System.err.println(user);
	}
	
	

	@Test
	public void updatelastErrorLogintime() throws ParseException {
		User user = new User();
		Date date = new Date();
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String dateTime = format.format(date);// Formats a Date into a date/time string.
		Date thisErrorTime = format.parse(dateTime);
		String userlogin = "18764513330";
		Integer count = 2;
		Integer locked = 0;
		
		user.setUserlogin(userlogin);
		user.setLastLoginErrorTime(thisErrorTime);
		user.setLoginErrorCount(count);
		user.setIslocked(1);
		Integer rows = mapper.updateErrorLogin(user);
		System.out.println("受影响的行数:::"+rows);
	}
	
	
	
	@Test
	public void updateInfo() {
		User user = new User();
		String phone = "18764513330";
		user.setUserlogin(phone);
		user.setPid(5);
		user.setMobile("17705302050");
		user.setIdcard("37290119880719203X");
		user.setRealName("王文跃");
		user.setUserNickname("雨的去向");
		Integer rows = mapper.updateInfo(user);
		System.out.println("rows="+rows);
	}
	
	
	
}
