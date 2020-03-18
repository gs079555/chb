package com.jiufukameng.service.ex;

public class UsernameDuplicateException extends ServiceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3093419479800594627L;

	public UsernameDuplicateException() {
		super();
	}

	public UsernameDuplicateException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public UsernameDuplicateException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public UsernameDuplicateException(String arg0) {
		super(arg0);
	}

	public UsernameDuplicateException(Throwable arg0) {
		super(arg0);
	}
	
	
}
