package com.async.http.exception;

public class CancledOrInterruptedExcetion  extends HttpException{

	 /**
	 * �û�ȡ�������������
	 */
	private static final long serialVersionUID = 1L;

	public CancledOrInterruptedExcetion() {
		// TODO Auto-generated constructor stub
		 super("the request cancled by user");
	}

}
