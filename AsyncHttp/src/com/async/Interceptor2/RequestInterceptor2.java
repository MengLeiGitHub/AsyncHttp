package com.async.Interceptor2;


import com.async.request2.BaseHttpRequest;


public class RequestInterceptor2  {

private  RequestInterceptorActionInterface requestInterceptor;
RequestInterceptor2  requestInterceptor2;
	
	public RequestInterceptor2(RequestInterceptorActionInterface  requestInterceptor){

		this.requestInterceptor=requestInterceptor;
	}


	public RequestInterceptor2 setRequestInterceptor(RequestInterceptorActionInterface requestInterceptor,RequestInterceptor2  requestInterceptor2) {
		if(this.requestInterceptor==null)
		this.requestInterceptor = requestInterceptor;
		this.requestInterceptor2=requestInterceptor2;
		return this;
	}

	public RequestInterceptor2 setRequestInterceptor2(RequestInterceptor2 requestInterceptor2) {
		this.requestInterceptor2 = requestInterceptor2;
		return this;
	}
	
	public BaseHttpRequest interceptor(BaseHttpRequest baseHttpRequest) throws Exception {
		if (requestInterceptor2!=null) {
			baseHttpRequest= requestInterceptor2.interceptor(baseHttpRequest);
 		 }
		if(requestInterceptor!=null){
			return requestInterceptor.interceptorAction(baseHttpRequest);
		}
 		return baseHttpRequest;
	}
}
