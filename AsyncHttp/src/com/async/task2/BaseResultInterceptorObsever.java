package com.async.task2;




import com.async.Interceptor2.ResponseInterceptor2;
import com.async.callback.HttpCallBack;
import com.async.entity.ResponseBody;
import com.async.msg.ResultObsever;

public class BaseResultInterceptorObsever<T> implements ResultObsever<T> {
	
	private  ResponseInterceptor2<T> responseInterceptor2;
	
	private  HttpCallBack<ResponseBody<T>>   httpCallBack;

	public void setResponseInterceptor2(ResponseInterceptor2<T> responseInterceptor2) {
		this.responseInterceptor2 = responseInterceptor2;
	}

	public  void setHttpCallBack(HttpCallBack<ResponseBody<T>> httpCallBack) {
		this.httpCallBack = httpCallBack;
	}
 
	@SuppressWarnings("unchecked")
	public void setResult(T arg0) {
		// TODO Auto-generated method stub
			try {
				if(responseInterceptor2!=null)
				arg0=responseInterceptor2.interceptor(arg0);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				httpCallBack.fail(e);
				return ;
			}
			ResponseBody<T> res=	(ResponseBody<T>) arg0;
			if(res!=null&&res.getException()==null)
				httpCallBack.success((ResponseBody<T>) arg0);
			else
				httpCallBack.fail(res.getException()); 
			
 	}

	 

}
