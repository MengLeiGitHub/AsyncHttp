package com.async.http.Interceptor2;

 import com.async.http.request2.BaseRequest;

public interface RequestInterceptorActionInterface {
	public <T> BaseRequest<T> interceptorAction(BaseRequest<T> baserequest) throws Exception;
}
