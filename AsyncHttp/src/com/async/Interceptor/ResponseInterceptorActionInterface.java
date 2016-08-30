package com.async.Interceptor;

import com.async.builder.ResponseBuilder;

public interface ResponseInterceptorActionInterface {

	public  ResponseBuilder interceptorAction(ResponseBuilder builder) throws Exception;
	
}
