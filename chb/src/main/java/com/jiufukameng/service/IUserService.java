package com.jiufukameng.service;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jiufukameng.entity.SmsParams;
import com.jiufukameng.entity.User;
import com.jiufukameng.service.ex.InsertException;
import com.jiufukameng.service.ex.PasswordNotMatchException;
import com.jiufukameng.service.ex.UpdateException;
import com.jiufukameng.service.ex.UsernameDuplicateException;
import com.jiufukameng.service.ex.VerifyCodeincorrectException;
import com.jiufukameng.service.ex.UserNotFoundException;

public interface IUserService {
	/**
	 * 用户注册
	 * @param user 用户数据对象
	 * @return 
	 * @throws UsernameDuplicateException 手机号已经被占用
	 * @throws InsertException 插入用户数据失败的异常
	 */
	 void reg(User user,SmsParams smsParams,HttpServletRequest request) throws UsernameDuplicateException,InsertException,VerifyCodeincorrectException;
	
	/**
	 * 用户登录
	 * @param userlogin 用户名
	 * @param userpass 密码
	 * @return 返回信息
	 * @throws UserNotFoundException 用户名错误
	 * @throws PasswordNotMatchException 密码错误
	 * @throws ParseException 
	 */
	User login(User user,HttpServletRequest request,HttpSession session) throws UserNotFoundException,PasswordNotMatchException,IOException, Exception;
	
	/**
	 *  修改密码
	 * @param id 用户id
	 * @param userlogin 用户名
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @throws UserNotFoundException 用户名不存在
	 * @throws PasswordNotMatchException 密码不正确
	 * @throws UpdateException 密码更新失败
	 */
	void changePassword( Integer id,String userlogin,String oldPassword,String newPassword) throws UserNotFoundException,PasswordNotMatchException,UpdateException;

	/**
	 *  遗忘密码
	 * @param user 
	 * @param smsParams
	 * @param request 
	 * @throws UserNotFoundException  用户名不存在异常
	 * @throws VerifyCodeincorrectException 验证码错误异常
	 */
	void forgetUserpass(User user,SmsParams smsParams,HttpServletRequest request) throws UserNotFoundException,VerifyCodeincorrectException;
	/**
	 * 查询用户ID
	 * @param id
	 * @return
	 */
	User getByid(Integer id);
	/**
	 * 更新用户数据
	 * @param user
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeInfo(User user) throws UserNotFoundException,UpdateException;
	
	/**
	 * 修改头像
	 * @param userlogin 用户名
	 * @param avatar 头像的路径
	 * @throws UserNotFoundException 用户数据不存在，或者已经被标记为删除
	 * @throws UpdateException 更新数据失败
	 */
	void changeAvatar(String userlogin,String avatar)throws UserNotFoundException,UpdateException;
	
	
	
}
