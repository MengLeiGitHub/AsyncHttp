package com.async.http.Interceptor2;

 import com.async.http.request2.BaseHttpRequest;

public interface RequestInterceptorActionInterface {
	public <T> BaseHttpRequest<T> interceptorAction(BaseHttpRequest<T> baserequest ) throws Exception;
}
