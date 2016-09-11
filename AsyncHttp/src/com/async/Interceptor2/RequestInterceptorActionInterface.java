package com.async.Interceptor2;

 import com.async.request2.BaseHttpRequest;

public interface RequestInterceptorActionInterface {
	public <T> BaseHttpRequest<T> interceptorAction(BaseHttpRequest<T> baserequest ) throws Exception;
}
