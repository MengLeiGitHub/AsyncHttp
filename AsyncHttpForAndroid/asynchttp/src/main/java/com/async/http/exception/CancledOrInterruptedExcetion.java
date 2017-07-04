package com.async.http.exception;

public class CancledOrInterruptedExcetion  extends HttpException{

	/**
	 * 用户取消请求或打断请求
	 */
	private static final long serialVersionUID = 1L;

	public CancledOrInterruptedExcetion() {
		// TODO Auto-generated constructor stub
		super("the request cancled by user");
	}

}
