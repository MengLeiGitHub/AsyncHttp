package com.async.http.Interceptor2;

import com.async.http.request2.BaseRequest;

public class RequestInterceptor2 {

	private RequestInterceptorActionInterface requestInterceptor;
	RequestInterceptor2 requestInterceptor2;

	public RequestInterceptor2(
			RequestInterceptorActionInterface requestInterceptor) {

		this.requestInterceptor = requestInterceptor;
	}

	public RequestInterceptor2 setRequestInterceptor(
			RequestInterceptorActionInterface requestInterceptor,
			RequestInterceptor2 requestInterceptor2) {
		if (this.requestInterceptor == null)
			this.requestInterceptor = requestInterceptor;
		this.requestInterceptor2 = requestInterceptor2;
		return this;
	}

	public RequestInterceptor2 setRequestInterceptor2(
			RequestInterceptor2 requestInterceptor2) {
		this.requestInterceptor2 = requestInterceptor2;
		return this;
	}

	public BaseRequest interceptor(BaseRequest baseRequest)
			throws Exception {
		if (requestInterceptor2 != null) {
			baseRequest = requestInterceptor2.interceptor(baseRequest);
		}
		if (requestInterceptor != null) {
			return requestInterceptor.interceptorAction(baseRequest);
		}
		return baseRequest;
	}
}
