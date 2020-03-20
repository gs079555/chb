package com.jiufukameng.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jiufukameng.controller.ex.FileEmptyException;
import com.jiufukameng.controller.ex.FileSizeException;
import com.jiufukameng.controller.ex.FileTypeException;
import com.jiufukameng.controller.ex.FileUploadIOException;
import com.jiufukameng.controller.ex.FileUploadStateException;
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
	/**
	 * 上传的头像的最大大小
	 */
	public static final long AVATAR_MAX_SIZE = 2*1024*1024;
	/**
	 * 上传时允许的头像文件的类型
	 */
	public static final List<String> AVATAR_CONTENT_TYPES = new ArrayList<>();
	/**
	 * 上传时的路径
	 */
	public static final String AVATAR_DIR = "upload";
	/**
	 * 初始化上传时允许的文件类型
	 */
	static {
		AVATAR_CONTENT_TYPES.add("image/jpeg");
		AVATAR_CONTENT_TYPES.add("image/png");
	}
	
	
	
	
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
		System.err.println(user);
		//向Session中封装数据
		session.setAttribute("id", user.getId());
		session.setAttribute("userlogin", user.getUserlogin());
		session.setAttribute("username", user.getUsername());
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
		Integer id = getIdFromSession(session);
		//查询匹配的数据
		User date = userService.getByid(id);
		//响应
		return new JsonResult<User>(SUCCESS,date);
	}
	
	@RequestMapping("change_info")
	public JsonResult<Void> changeInfo(User user,HttpSession session){
		//从session中获取id和userlogin
		Integer id = getIdFromSession(session);
		String userlogin = getUserloginFromSession(session);
		//将id和userlogin封装到user中
		user.setId(id);
		user.setUserlogin(userlogin);
		//执行修改
		userService.changeInfo(user);
		//响应
		return new JsonResult<>(SUCCESS);
	}
	
	@PostMapping("change_avatar")
	public JsonResult<String> changeAvatar(HttpServletRequest request,@RequestParam("file") MultipartFile file){
		//检查文件是否为空
		if(file.isEmpty()) {
			throw new FileEmptyException("上传失败,请选择有效文件");
		}
		//检查文件大小
		if(file.getSize()>AVATAR_MAX_SIZE) {
			throw new FileSizeException("文件大小超过限制"+AVATAR_MAX_SIZE/1024+"KB,请重新上传");
		}
		//检查文件类型 
		if(!AVATAR_CONTENT_TYPES.contains(file.getContentType())) {
			throw new FileTypeException("上传失败!仅支持以下类型的图片文件:"+AVATAR_CONTENT_TYPES);
		}
		//确定文件夹
		String dirPath = request.getServletContext().getRealPath(AVATAR_DIR);
		File dir = new File(dirPath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		//确定文件名
		String originalFilename = file.getOriginalFilename();
		String suffix="";
		int beginIndex = originalFilename.lastIndexOf(".");
		if(beginIndex !=-1) {
			suffix = originalFilename.substring(beginIndex);
		}
		System.err.println("suffix"+suffix);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		long ms = System.currentTimeMillis(); 
		String filename = format.format(date)+ms+suffix;
		//执行保存
		File dest = new File(dir,filename);
		System.out.println("dizhi++++"+dir);
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new FileUploadStateException("上传失败!请检查原文件是否存在并可访问");
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileUploadIOException("上传失败!读取数据时出现未知错误");
		}
		//更新数据表
		String avatar = "/"+AVATAR_DIR+"/"+filename;
		HttpSession session = request.getSession();
		String userlogin = getUserloginFromSession(session);
		userService.changeAvatar(userlogin, avatar);
		
		//返回
		JsonResult<String> jr = new JsonResult<String>();
		jr.setState(SUCCESS);
		jr.setData(avatar);
		return jr;
	}
	
	
	
	
	
	
}
