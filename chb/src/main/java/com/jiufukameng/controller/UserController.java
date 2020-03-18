package com.jiufukameng.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jiufukameng.entity.SmsParams;
import com.jiufukameng.entity.User;
import com.jiufukameng.service.IUserService;
import com.jiufukameng.service.TxCloudSms;
import com.jiufukameng.service.ex.PasswordNotMatchException;
import com.jiufukameng.service.ex.UserNotFoundException;
import com.jiufukameng.util.JsonResult;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{
	@Autowired
	private IUserService userService;
	
	@Autowired
	TxCloudSms txCloudSms;
	
	@ResponseBody
	@RequestMapping("reg")
	public JsonResult<Void> reg(User user,SmsParams smsParams,HttpServletRequest request){
		
		//执行注册
		userService.reg(user,smsParams, request);
		//响应操作成功
		return new JsonResult<Void>(SUCCESS);
	}
		
	
	@RequestMapping("sms")
	public JsonResult<Void> sms(HttpServletRequest request,SmsParams smsParams){
		
		//执行短信验证码
		txCloudSms.sendSms(request, smsParams);
		return new JsonResult<Void>(SUCCESS);
	}
	
	@RequestMapping("login")
	public JsonResult<User> login(User user,HttpServletRequest request,HttpSession session) throws UserNotFoundException, PasswordNotMatchException, Exception{
		//执行登录，获取登录返回结果
		
		user = userService.login(user,request, session);
		//向Session中封装数据
		session.setAttribute("id", user.getId());
		session.setAttribute("userlogin", user.getUserlogin());
		//向客户端响应操作成功
		return new JsonResult<>(SUCCESS,user);
	}
	
	
	
	
	
	
	@RequestMapping("change_userpass")
	public JsonResult<Void> changePassword(@RequestParam("old_userpass") String olduserpass,@RequestParam("new_userpass") String newuserpass,HttpSession session){
		//从session中获取uid和userlogin
		Integer uid = Integer.valueOf(session.getAttribute("id").toString());
//		System.out.println("uid"+uid);
		String userlogin = session.getAttribute("userlogin").toString();
		//执行修改密码
		userService.changePassword(uid,userlogin, olduserpass, newuserpass);
		//响应修改成功
		return new JsonResult<Void>(SUCCESS);
	}
	
	@RequestMapping("forget_password")
	public JsonResult<Void> forgetPassword(User user,SmsParams smsParams,HttpServletRequest request,HttpSession session){
		String users = user.getUserlogin();
		System.out.println("jsjsjsjsj"+users);
		
		String userlogin = session.getAttribute("user").toString();
		System.out.println("userlogin:::"+userlogin);
		userService.forgetUserpass(user, smsParams, request);
		
		return new JsonResult<Void>(SUCCESS);
	}
	
	@GetMapping("get_info")
	public JsonResult<User> getById(HttpSession session){
		//从Session中获取id
		Integer id = getidFromSession(session);
		//查询匹配的数据
		User date = userService.getByid(id);
		//响应
		return new JsonResult<User>(SUCCESS,date);
	}
	
	@RequestMapping("change_info")
	public JsonResult<Void> changeInfo(User user,HttpSession session){
		//从session中获取id和userlogin
		Integer id = getidFromSession(session);
		String userlogin = getUserloginFromSession(session);
		//将id和userlogin封装到user中
		user.setId(id);
		user.setUserlogin(userlogin);
		//执行修改
		userService.changeInfo(user);
		//响应
		return new JsonResult<>(SUCCESS);
	}
	
	
}
