package com.async.Interceptor2;

 
public interface ResponseInterceptorActionInterface<T> {

	public  T interceptorAction(T t) throws Exception;
	
}
