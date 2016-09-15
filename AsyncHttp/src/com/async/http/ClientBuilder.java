package com.async.http;

import com.async.http.Interceptor2.RequestInterceptorActionInterface;
import com.async.http.Interceptor2.ResponseInterceptorActionInterface;

 
public class ClientBuilder {

	private AsyncHttp mHttpClient;

	static ClientBuilder clientBuilder;

	public ClientBuilder getInstance() {
		if (clientBuilder == null) {
			clientBuilder = new ClientBuilder();
		}
		mHttpClient = new AsyncHttp();
		return clientBuilder;
	}

	public  ClientBuilder addRequestInterceptor(
			RequestInterceptorActionInterface  requestInterceptor) {
		mHttpClient.addRequestInterceptor(requestInterceptor);
		return clientBuilder;
	}

	public ClientBuilder addResponseInterceptor(
			ResponseInterceptorActionInterface responseInterceptor) {
		mHttpClient.addResponseInterceptor(responseInterceptor);

		return clientBuilder;
	}

}
