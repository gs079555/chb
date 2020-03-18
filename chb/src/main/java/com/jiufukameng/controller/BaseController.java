package com.jiufukameng.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jiufukameng.service.ex.InsertException;
import com.jiufukameng.service.ex.PasswordNotMatchException;
import com.jiufukameng.service.ex.ServiceException;
import com.jiufukameng.service.ex.UpdateException;
import com.jiufukameng.service.ex.UserNotFoundException;
import com.jiufukameng.service.ex.UsernameDuplicateException;
import com.jiufukameng.util.JsonResult;


public class BaseController {
	/**
	 * 操作结果的“成功状态”
	 */
	public static final Integer SUCCESS = 200;
	/**
	 * 从Session中获取当前登录的用户的ID
	 * @param session
	 * @return 当前登录的用户的id
	 */
	protected final Integer getidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("id").toString());
	}
	
	protected final String getUserloginFromSession(HttpSession session) {
		return session.getAttribute("userlogin").toString();
	}
	
	@ExceptionHandler(ServiceException.class)
	public JsonResult<Void> handleException(Throwable e){
		JsonResult<Void> jr = new JsonResult<Void>();
		jr.setMessage(e.getMessage());
		
		if (e instanceof UsernameDuplicateException) {
			// 4000-用户名冲突异常，例如注册时用户名已经被占用
			jr.setState(4000);
		} else if (e instanceof UserNotFoundException) {
			// 4001-用户数据不存在
			jr.setState(4001);
		} else if (e instanceof PasswordNotMatchException) {
			// 4002-验证密码失败
			jr.setState(4002);
		} else if (e instanceof InsertException) {
			// 5000-插入数据异常
			jr.setState(5000);
		} else if (e instanceof UpdateException) {
			// 5001-更新数据异常
			jr.setState(5001);
		}
		return jr;
	}
}
