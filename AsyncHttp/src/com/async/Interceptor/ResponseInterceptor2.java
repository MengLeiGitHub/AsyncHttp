package com.async.Interceptor;

import com.async.builder.ResponseBuilder;

public class ResponseInterceptor2 {
	private ResponseInterceptorActionInterface responseInterceptorActionInterface;
	private ResponseInterceptor2 responseInterceptor2;

	public ResponseInterceptor2(ResponseInterceptorActionInterface responseInterceptorActionInterface) {
		// TODO Auto-generated constructor stub
		this.responseInterceptorActionInterface=responseInterceptorActionInterface;

	}

	public ResponseInterceptor2 setResponseInterceptor(
			ResponseInterceptor2 responseInterceptor) {
		if (this.responseInterceptor2 == null)
			this.responseInterceptor2 = responseInterceptor;
		else
			this.responseInterceptor2
					.setResponseInterceptor(responseInterceptor);
		return responseInterceptor;
	}

	public ResponseInterceptor2 setResponseInterceptor2(
			ResponseInterceptor2 responseInterceptor2) {
		this.responseInterceptor2 = responseInterceptor2;
		return this;
	}
	
	
	public ResponseBuilder interceptor(ResponseBuilder builder)
			throws Exception {
		if (responseInterceptor2 != null) {
			builder = responseInterceptor2.interceptor(builder);
			
		}
		if (responseInterceptorActionInterface != null) {
			return responseInterceptorActionInterface
					.interceptorAction(builder);
		}
		 return builder;
	}

	 

}
