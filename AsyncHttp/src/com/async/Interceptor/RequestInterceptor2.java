package com.async.Interceptor;

import com.async.builder.RequestBuilder;
import com.async.builder.ResponseBuilder;
import com.async.entity.RequestBody;


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
	
	public RequestBuilder interceptor(RequestBuilder builder) throws Exception {
		if (requestInterceptor2!=null) {
			builder= requestInterceptor2.interceptor(builder);
 		 }
		if(requestInterceptor!=null){
			return requestInterceptor.interceptorAction(builder);
		}
 		return builder;
	}
}
