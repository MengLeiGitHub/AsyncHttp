package com.async.task;




import com.async.Interceptor.ResponseInterceptor2;
import com.async.builder.ResponseBuilder;
import com.async.callback.HttpCallBack;
import com.async.msg.ResultObsever;

public class StringResultInterceptorObsever implements ResultObsever<String> {
	
	private  ResponseInterceptor2 responseInterceptor2;
	
	private  HttpCallBack<String>   httpCallBack;

	public void setResponseInterceptor2(ResponseInterceptor2 responseInterceptor2) {
		this.responseInterceptor2 = responseInterceptor2;
	}

	public void setHttpCallBack(HttpCallBack<String> httpCallBack) {
		this.httpCallBack = httpCallBack;
	}


	@SuppressWarnings("unchecked")
	public void setResult(String arg0) {
		// TODO Auto-generated method stub
		
		ResponseBuilder<String> responseBuilder;
			if(responseInterceptor2!=null){
				try {
					responseBuilder=responseInterceptor2.interceptor(new ResponseBuilder<String>().setResult( arg0));
					arg0=responseBuilder.response().getResult();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
 		    }
			
			httpCallBack.success(arg0);
			
 	}

	 

}
