package com.jiufukameng.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.jiufukameng.entity.SmsParams;
import com.jiufukameng.entity.User;
import com.jiufukameng.mapper.UserMapper;
import com.jiufukameng.service.IUserService;
import com.jiufukameng.service.ex.InsertException;
import com.jiufukameng.service.ex.PasswordNotMatchException;
import com.jiufukameng.service.ex.UpdateException;
import com.jiufukameng.service.ex.UserNotFoundException;
import com.jiufukameng.service.ex.UsernameDuplicateException;
import com.jiufukameng.service.ex.VerifyCodeincorrectException;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private TxCloudSmsImpl txCloudSmsImpl;
	
	
	
	@Override
	public void reg(User user,SmsParams smsParams,HttpServletRequest request)
			throws UsernameDuplicateException, InsertException, VerifyCodeincorrectException {
		
		
		HttpSession session = request.getSession();
		 
		//根据参数user对象中的userlogin属性查询数据：userMapper.findByUsername()
		String userlogin = user.getUserlogin();
		Integer pid = user.getId();
		User result = userMapper.findByUserlogin(userlogin);
		// 判断查询结果是否不为null（查询结果是存在的）
		if (result != null) {
			// 是：用户名已被占用，抛出UsernameDuplicateException
			throw new UsernameDuplicateException("注册失败！尝试注册的手机号(" + userlogin + ")已经被 占用");
		}
		//推荐 人PID
		Object phones = session.getAttribute("user");
		Object code = session.getAttribute("code");
		Object md5VerifyCode = txCloudSmsImpl.getMd5VerifyCode(smsParams.getVerifyCode());
		//判断验证码是否正确
        if (code==null||!code.equals(md5VerifyCode)) {
        	throw new VerifyCodeincorrectException("验证码不正确");
        }
        if (!phones.equals(userlogin)) {
			throw new UsernameDuplicateException("请先获取验证码");
		} 
        Date date = new Date();
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//		String dateTime = format.format(date);// Formats a Date into a date/time string.
//		Date thisErrorTime = format.parse(dateTime);
        //对比一次验证码后将验证码从session中删除
        session.removeAttribute("code");
        Object codes = session.getAttribute("code");
		// TODO 得到盐值
		System.err.println("reg()>>userpass=" + user.getUserpass());
		String salt = UUID.randomUUID().toString().toUpperCase();
		// TODO 基于参数user对象中的password进行加密，得到加密后的密码
		String md5Password = getMd5Password(user.getUserpass(), salt);
		System.err.println("password==" + user.getUserpass());
		// TODO 将加密后的密码和盐值封装到user中
		user.setPid(pid);
		user.setSalt(salt);
		user.setCreateTime(date);
		user.setUserlogin(userlogin);
		user.setMobile(userlogin);
		user.setUsername(userlogin);
		user.setUserpass(md5Password);
		System.err.println("reg()>>salt=" + salt);
		System.err.println("reg()>>md5password=" + md5Password);

		// 执行注册：userMapper.insert(user)
		Integer rows = userMapper.insert(user);
		if (rows != 1) {
			throw new InsertException("注册失败！写入数据出现未知错误！请联系系统管理员");
		}
	}

	@Override
	public User login(User user,HttpServletRequest request,HttpSession session) throws UserNotFoundException,PasswordNotMatchException,IOException, Exception {

		// 根据参数usernlogin执行查询用户数据
		// 从查询结果中获取盐值
		User result = userMapper.findByUserlogin(user.getUserlogin());
		// 判断查询结果是否为null
		if (user.getUserlogin()=="") {
			throw new UserNotFoundException("用户名不能为空");
		}
		if (user.getUserpass()=="") {
			throw new UserNotFoundException("密码不能为空");
		}
		if(result==null) {
			throw new UserNotFoundException("用户名不存在或密码不正确");
		}
		String yzmcode = user.getYzmCode(); //获取前端验证码
		System.out.println("前端输入验证码"+yzmcode);
		
		Object code = session.getAttribute("TEST_YZM");
		System.out.println("生成的验证码"+code);
		
		Date thisErrorTime=null; //修改的首次登录错误时间
		Integer islocked = 0; //获取是否锁定状态
		
		// 根据参数password和盐值一起进行加密，得到加密后的密码
		String salt = result.getSalt();
		String md5Password = getMd5Password(user.getUserpass(), salt);
		
		int m=3; //判断剩余输入账号密码的机会
		Integer loginErrorCount = 0;//初始化失败次数
		Integer Count=6; 	//允许错误次数
		Long timeSlot = 0L;
		Date date = new Date();
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String dateTime = format.format(date);// Formats a Date into a date/time string.
		thisErrorTime = format.parse(dateTime);
		// 判断密码是否不一致
			if (!md5Password.equals(result.getUserpass())) {
				System.err.println("错误.>>>>>result>>>>>>>>>"+result);
				System.err.println("错误user.getislocked::::::::::::::::"+result.getIslocked());
				System.err.println("错误result.getlastloginerrortime::::"+result.getLastLoginErrorTime());
				System.err.println("错误result.getlastlogintime:::::::::"+result.getLastLoginErrorTime());
				System.err.println("thiserrortime:::::::::::::::::::::::"+thisErrorTime.getTime());
				if(result.getIslocked() == null) {
					result.setIslocked(0);
				}else {
					islocked = result.getIslocked();
					System.err.println("islocked::"+islocked);
				}
				System.err.println("else外islocked::"+islocked);
				
				if(result.getLoginErrorCount() == null) {
					result.setLoginErrorCount(0);
				}else {
					loginErrorCount = result.getLoginErrorCount();
					System.err.println("错误次数:"+loginErrorCount);
				}
				
				
				Date lastLoginErrorTime=null; //最后一次登录错误时间
				if(islocked == 1) {
					if(result.getLastLoginErrorTime() == null) {
						lastLoginErrorTime = thisErrorTime;
						System.err.println("锁定状态为Null::"+lastLoginErrorTime.getTime());
					}else {
						lastLoginErrorTime = result.getLastLoginErrorTime();
						timeSlot = new Date().getTime() - lastLoginErrorTime.getTime();
						System.out.println("timeSLOT:::"+timeSlot);
						System.out.println("thisErrorTime.gettime"+thisErrorTime.getTime());
						System.out.println("lastloginErrorTime::嘿嘿:"+lastLoginErrorTime.getTime());
					}
					if(timeSlot < 5*60*1000&&timeSlot>=0) { //判断最后锁定时间,5分钟之内继续锁定
						System.out.println("用户被锁定,请"+((int)(Count - Math.ceil((double)timeSlot/60000)))+"分钟后再试1");
						throw new PasswordNotMatchException("用户被锁定,请"+((int)(Count - Math.ceil((double)timeSlot/60000)))+"分钟后再试"); 
					}else {
						result.setLastLoginErrorTime(thisErrorTime);
						userMapper.updateErrorLogin(result);
						System.out.println("用户被锁定,请"+((int)(Count - Math.ceil((double)timeSlot/60000)))+"分钟后再试2");
						throw new PasswordNotMatchException("用户被锁定,请5分钟后再试"); 
					}
					
				}else if(result.getLoginErrorCount() == 4) { //账户第五次登陆失录,此时登录错误数增加至5,以后错误仍是5,不再递增
					result.setLoginErrorCount(5);
					result.setIslocked(1);
					result.setLastLoginErrorTime(thisErrorTime);
					userMapper.updateErrorLogin(result);	//修改用户
					System.out.println("用户被锁定,请"+((int)(Count - Math.ceil((double)timeSlot/60000)))+"分钟后再试3");
					System.err.println("3timeslot"+timeSlot);
					throw new PasswordNotMatchException("用户被锁定,请"+((int)(Count - Math.ceil((double)timeSlot/60000)))+"分钟后再试"); 
					
				}else {	//账户前四次登录失败
					if(result.getLoginErrorCount()<5) {
						if(result.getLoginErrorCount()>=3) {
							if (!yzmcode.equals(code)) {
								throw new UserNotFoundException("验证码不正确");
							}
						}
						result.setLoginErrorCount(result.getLoginErrorCount()+1);
						result.setLastLoginErrorTime(thisErrorTime);
						userMapper.updateErrorLogin(result);	//修改用户
						System.out.println("账号或密码有错误 ,您还有"+(4-loginErrorCount)+"次登录机会4");
						throw new PasswordNotMatchException("账号或密码有错误 ,您还有"+(4-loginErrorCount)+"次登录机会");
					}else if(((int)(Count - Math.ceil((double)timeSlot/60000)))<=0) {
						throw new PasswordNotMatchException("用户被锁定,请"+((int)(Count - Math.ceil((double)timeSlot/60000)))+"分钟后再试");
					}
				}
				
				
				// 抛出：PasswordNotMatchException
				System.out.println("登失败啦!");
				System.err.println("timeslot:::"+timeSlot);
				throw new PasswordNotMatchException("用户被锁定,请"+((int)(Count - Math.ceil((double)timeSlot/60000)))+"分钟后再试");
			}else {
				islocked = result.getIslocked();
				if (islocked == 1) {
					Date lastLoginErrorTime = null; //最后一次登陆错误时间
					if(result.getLastLoginErrorTime() == null) {
						lastLoginErrorTime = new Date();
					}else {
						lastLoginErrorTime = result.getLastLoginErrorTime();
						timeSlot = new Date().getTime()- lastLoginErrorTime.getTime();	
					}
					if(timeSlot<5*60*1000&&timeSlot>=0) {	//判断最后锁定时间,30分钟之内继续锁定
							System.out.println("您的账户已经被锁定,请"+((int)(Count - Math.ceil((double)timeSlot/60000)))+"分钟后再试5");
							throw new PasswordNotMatchException("您的账户已经被锁定,请"+((int)(Count - Math.ceil((double)timeSlot/60000)))+"分钟后再试5");
					}else {
						System.err.println("走else啦....");
						Date d = new Date();
						result.setLoginErrorCount(0);
						result.setIslocked(0);
						result.setLastLoginTime(d);
						session.setAttribute("user", result);//保存当前用户
						session.setAttribute("dateStr", d);//保存当前用户登录时间用于显示
//						result.setLastLoginIp(ServletActionContext.getRequest().getRemoteAddr());
						userMapper.updateErrorLogin(result);	//修改用户表登录时间
						throw new PasswordNotMatchException("正确录");
					}
				}
				
			}
			
		//设置登录错误错误次数为0
		result.setLoginErrorCount(0);
		//存入登录时间
		result.setLastLoginTime(thisErrorTime);
		session.setAttribute("user", result);//保存当前用户
		session.setAttribute("dateStr", thisErrorTime);//保存当前用户登录时间用于显示
//		result.setLastLoginIp(ServletActionContext.getRequest().getRemoteAddr());
//		result.setLastLoginTime(thisErrorTime);
//		result.setLoginErrorCount(loginErrorCount); //写入失败次数
		// 将查询结果中的userpass、salt设置为null
		result.setUserpass(null);
		result.setSalt(null);
		userMapper.updateErrorLogin(result);
		// 返回查询结果
		return result;
	}

	@Override
	public void changePassword(Integer id,String userlogin, String olduserpass, String newuserpass)
			throws UserNotFoundException, PasswordNotMatchException, UpdateException {
		System.err.println("changePassword() ----> BEGIN:");
		System.err.println("changePassword() 原密码=" + olduserpass);
		System.err.println("changePassword() 新密码=" + newuserpass);

		// 根据参数userlogin查询用户数据
		User result = userMapper.findByUserlogin(userlogin);
		// 判断查询结果是否为null
		if (result == null) {
			// 抛出：UserNotFoundException
			throw new UserNotFoundException("修改密码失败!用户名不存在!");
		}
		// 从查询结果中获取盐值
		String salt = result.getSalt();
		// 根据参数oldPassword和盐值一起进行加密，得到加密后的密码
		String oldMd5Password = getMd5Password(olduserpass, salt);
		System.err.println("changePassword() 盐值=" + salt);
		System.err.println("changePassword() 原始密码加密=" + oldMd5Password);
		System.err.println("changePassword() 正确密码=" + result.getUserpass());
		// 判断查询结果中的userpass和以上加密后的密码是否不一致
		if (!result.getUserpass().equals(oldMd5Password)) {
			// 抛出：PasswordNotMatchException
			throw new PasswordNotMatchException("修改密码失败!原密码错误");
		}

		// 根据参数newPassword和盐值一起进行加密，得到加密后的密码
		String newMd5Password = getMd5Password(newuserpass, salt);
		System.err.println("changePassword() 新密码加密=" + newMd5Password);

		// 执行更新密码，并获取返回的受影响的行数
		Integer rows = userMapper.updatePassword(userlogin, newMd5Password);
		// 判断受影响的行数是否不为1
		if (rows != 1) {
			// 抛出：UpdateException
			throw new UpdateException("修改密码失败!更新密码出现未知错误!");
		}
		System.err.println("changePassword() ---> END.");
	}
	
	@Override
	public void forgetUserpass(User user,SmsParams smsParams,HttpServletRequest request)throws UserNotFoundException, VerifyCodeincorrectException {
		HttpSession session = request.getSession();
		//根据参数user对象中的userlogin属性查询数据：userMapper.findByUsername()
		User result = userMapper.findByUserlogin(user.getUserlogin());
	
		String userlogin = result.getUserlogin();
		Object code = session.getAttribute("code");
		Object md5VerifyCode = txCloudSmsImpl.getMd5VerifyCode(smsParams.getVerifyCode());
		//判断验证码是否正确
        if (code==null||!code.equals(md5VerifyCode)) {
        	throw new VerifyCodeincorrectException("验证码不正确");
        }
        
        //对比一次验证码后将验证码从session中删除
        session.removeAttribute("code");
        Object codes = session.getAttribute("code");
        
     		
     		// 获取前端输入的密码
     		String userpass = user.getUserpass();
     		//生成盐值
     		String salt = result.getSalt();
     		// 根据参数newPassword和盐值一起进行加密，得到加密后的密码
     		String newMd5Password = getMd5Password(userpass, salt);
     		//向user中写入新密码
     		user.setSalt(salt);
     		user.setUserpass(newMd5Password);
     		user.setIslocked(null);
     		user.setLoginErrorCount(null);
     		// 执行更新密码，并获取返回的受影响的行数
     		Integer rows = userMapper.updatePassword(userlogin, newMd5Password);
     		// 判断受影响的行数是否不为1
     		if (rows != 1) {
     			// 抛出：UpdateException
     			throw new UpdateException("修改密码失败!更新密码出现未知错误!");
     		}
     		System.err.println("changePassword() ---> END.");
        
        

	}
	
	@Override
	public User getByid(Integer id) {
		 // 根据uid查询用户数据
		User result = userMapper.findByid(id);
	    // 如果查询到数据，则需要将查询结果中的password、salt、is_delete设置为null
		if(result!=null) {
			result.setUserpass(null);
			result.setSalt(null);
			
		}
	    // 将查询结果返回
		return result;
	}

	@Override
	public void changeInfo(User user) throws UserNotFoundException, UpdateException {
		
		// 根据参数user中的uid，即user.getUserlogin()查询数据
		User result = userMapper.findByid(user.getId());
		// 检查查询结果是否存在，是否标记为删除
		//判断查询结果是否为Null
		if(result==null){
			throw new UserNotFoundException("修改个人资料失败！用户数据不存在");
		}
		if(result.getUserStatus() == 0) {
			//抛出：UserNotFoundException
			throw new UserNotFoundException("修改个人资料失败！用户数据不存在");
		}
		
		// 创建当前时间对象
		Date now = new Date();
		// 将时间封装到参数user中
		user.setModifiedTime(now);
		user.setModifiedUser(user.getUserlogin());
		// 执行修改个人资料：mapper.updateInfo(user) > update t_user set phone=?, email=?, gender=?, modified_user=?, modified_time=? where uid=?
		Integer rows = userMapper.updateInfo(user);
		// 判断以上修改时的返回值是否不为1
		if (rows!=1) {
			// 抛出：UpdateException 
			throw new UpdateException("修改个人资料失败！更新用户数据时出现未知错误");
		}
	}
	
	@Override
	public void changeAvatar(String userlogin, String avatar) throws UserNotFoundException, UpdateException {
		// 根据参数uid查询用户数据
		User result = userMapper.findByUserlogin(userlogin);
		System.err.println("result"+result);
	    // 判断查询结果是否为null
		if(result==null) {
			// 抛出：UserNotFoundException
			throw new UserNotFoundException("头像更新失败！用户不存在1");
		}
			
	    // 判断查询结果中的userStatus为0
		if(result.getUserStatus()==0) {
			// 抛出：UserNotFoundException
			throw new UserNotFoundException("头像更新失败！用户不存在2");
		}
	    // 创建当前时间对象
		Date now = new Date();
		
	    // 执行更新头像，并获取返回的受影响的行数
		Integer rows = userMapper.updateAvatar(userlogin, avatar, result.getUserlogin(), now);
	    // 判断受影响的行数是否不为1
		if(rows!=1) {
			// 抛出：UpdateException
			throw new UpdateException("修改头像失败，出现未知错误，请联系管理员");
		}
		
		
	}

	/**
	 * 对密码进行加密
	 * 
	 * @param password 原始密码
	 * @return 加密后的密码
	 */
	private String getMd5Password(String password, String salt) {
		// 规则：对password+salt进行3重加密
		String str = password + salt;
		for (int i = 0; i < 3; i++) {
			str = DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
		}
		return str;
	}







	

}
