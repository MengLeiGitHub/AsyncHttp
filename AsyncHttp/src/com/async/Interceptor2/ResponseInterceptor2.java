package com.async.Interceptor2;

public class ResponseInterceptor2<T> {
	private ResponseInterceptorActionInterface<T> responseInterceptorActionInterface;
	private ResponseInterceptor2<T> responseInterceptor2;

	public ResponseInterceptor2(
			ResponseInterceptorActionInterface responseInterceptorActionInterface) {
		// TODO Auto-generated constructor stub
		this.responseInterceptorActionInterface = responseInterceptorActionInterface;

	}

	public ResponseInterceptor2<T> setResponseInterceptor(
			ResponseInterceptor2<T> responseInterceptor) {
		if (this.responseInterceptor2 == null)
			this.responseInterceptor2 = responseInterceptor;
		else
			this.responseInterceptor2
					.setResponseInterceptor(responseInterceptor);
		return responseInterceptor;
	}

	public ResponseInterceptor2<T> setResponseInterceptor2(
			ResponseInterceptor2<T> responseInterceptor2) {
		this.responseInterceptor2 = responseInterceptor2;
		return this;
	}

	public T interceptor(T t) throws Exception {
		if (responseInterceptor2 != null) {
			t = responseInterceptor2.interceptor(t);

		}
		if (responseInterceptorActionInterface != null) {
			return responseInterceptorActionInterface.interceptorAction(t);
		}
		return t;
	}

}
