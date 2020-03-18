package com.jiufukameng.mapper;

import com.jiufukameng.entity.User;

import io.lettuce.core.dynamic.annotation.Param;


public interface UserMapper {
	/**
	 * 执行插入的数据
	 * @param user 需要插入的用户的数据
	 * @return 返回数据为空或不为空，为空则可插入，不为空则不允许插入
	 */
	Integer insert(User user);
	
	/**
	 *  查询用户名(手机号)是否被占用
	 * @param l 
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	User findByid(Integer id);
	
	/**
	 *  查询用户名
	 * @param userlogin
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	User findByUserlogin(String userlogin);
	
	
	
	
	
	/**
	 *  更新密码
	 * @param userlogin 用户名(手机号)
	 * @param userpass 密码
	 * @return 返回成功rows=1
	 */
	Integer updatePassword(@Param("userlogin") String userlogin,@Param("userpass") String userpass);
	
	
	
	/**
	 * 
	 *  @param userModel 0为普通用户 1为管理员
	 *  @param loginErrorCount 登录错误次数
	 *  @param firstLoginErrorTime 记录第一次登录错误时间
	 *  @param lastloginErrorTime 最后一次错误时间
	 *  @param islocked 更改加锁状态(0 未锁定,1 锁定)
	 *  @return rows
	 */
	
	Integer updateErrorLogin(User user);
	
	/**
	 * 修改用户资料
	 * @param user 用户信息
	 * @return 
	 */
	Integer updateInfo(User user);


	
}
