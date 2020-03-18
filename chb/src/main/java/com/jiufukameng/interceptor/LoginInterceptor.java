package com.jiufukameng.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor{
	
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {
		//验证用户是否登录,如果已录,放行,如果未登录,拦截,并重定向到登录页面
		//获取Session对象
		HttpSession session = request.getSession();
		//判断Session中有没有id,因为登录成功时往Session中存入了id,所以可以使用UID作为判断标准
		if(session.getAttribute("id")==null) {
			//重定向到登录页面
			response.sendRedirect("/web/login.html");
			//执行拦截
			return false;
		}
		//执行放行
		return true;
	}
	
}
