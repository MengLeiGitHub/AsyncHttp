package com.async;

import com.async.Interceptor.RequestInterceptorActionInterface;
import com.async.Interceptor.ResponseInterceptorActionInterface;

public class ClientBuilder {

	private MHttpClient mHttpClient;

	static ClientBuilder clientBuilder;

	public ClientBuilder getInstance() {
		if (clientBuilder == null) {
			clientBuilder = new ClientBuilder();
		}
		mHttpClient = new MHttpClient();
		return clientBuilder;
	}

	public ClientBuilder addRequestInterceptor(
			RequestInterceptorActionInterface requestInterceptor) {
		mHttpClient.addRequestInterceptor(requestInterceptor);
		return clientBuilder;
	}

	public ClientBuilder addResponseInterceptor(
			ResponseInterceptorActionInterface responseInterceptor) {
		mHttpClient.addResponseInterceptor(responseInterceptor);

		return clientBuilder;
	}

}
