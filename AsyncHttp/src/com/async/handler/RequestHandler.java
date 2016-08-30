package com.async.handler;

 
 
import com.async.Interceptor.RequestInterceptor2;
import com.async.builder.RequestBuilder;
import com.async.entity.URLConfig;
import com.async.request.HttpRequest;

  
 
public final class RequestHandler  {
	private HttpRequest httpRequest;

	private RequestBuilder requestBuilder;

	public RequestHandler(URLConfig config, HttpRequest httpRequest) {
		this.httpRequest = httpRequest;
		requestBuilder = new RequestBuilder().config(config);
	}

	public   String SendPost() {
		
		return	httpRequest.sendPost(requestBuilder);
	}

	public   String SendGet() {

		return httpRequest.sendGet(requestBuilder);
	}

	public void stop() {
		httpRequest.stop();
	}

	public void cancle() {
		httpRequest.cancle();
	}

	public void addRequestInterceptor(RequestInterceptor2 requestInterceptor) {
		// TODO Auto-generated method stub
		httpRequest.addRequestInterceptor(requestInterceptor);
	}

 
	public HttpRequest getHttpRequest() {
		return httpRequest;
	}

	public RequestBuilder getRequestBuilder() {
		return requestBuilder;
	}

}
