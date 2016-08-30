package com.async.Interceptor;

import com.async.builder.RequestBuilder;

public interface RequestInterceptorActionInterface {
	public  RequestBuilder interceptorAction(RequestBuilder builder) throws Exception;
}
