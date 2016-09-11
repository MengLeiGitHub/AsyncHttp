package com.async;

import com.async.SyncPoolExecutor;
 
import com.async.Interceptor2.RequestInterceptor2;
import com.async.Interceptor2.RequestInterceptorActionInterface;
import com.async.Interceptor2.ResponseInterceptor2;
import com.async.Interceptor2.ResponseInterceptorActionInterface;
import com.async.callback.HttpCallBack;
import com.async.clientImpl.HttpClientImpl;
import com.async.entity.ResponseBody;
import com.async.handler.TaskHandler;
import com.async.request2.BaseHttpRequest; 
import com.async.request2.RequestConfig;
import com.async.task2.BaseHttpTask;
import com.async.task2.BaseResultInterceptorObsever;

public class AsyncHttp {
	RequestInterceptor2 requestInterceptor;
	ResponseInterceptor2 responseInterceptor;

	private RequestConfig config;
	
	public void setConfig(RequestConfig config) {
		this.config = config;
	}
	public RequestConfig getConfig() {
		return config;
	}
	
	public AsyncHttp() {
		
		 SyncPoolExecutor.newFixedThreadPool(2, 1, null).isDebug(false);
  	}
	
	private static AsyncHttp mHttpClient;
	
	
	public static AsyncHttp instance(){
		if(mHttpClient==null)mHttpClient=new AsyncHttp();
		return mHttpClient;
	}
	
	

	public AsyncHttp addRequestInterceptor(
			RequestInterceptorActionInterface requestInterceptor) {
		if (this.requestInterceptor == null)
			this.requestInterceptor = new RequestInterceptor2(
					requestInterceptor);

		else
			this.requestInterceptor = new RequestInterceptor2(
					requestInterceptor)
					.setRequestInterceptor2(this.requestInterceptor);

		return this;
	}

	public AsyncHttp addResponseInterceptor(
			ResponseInterceptorActionInterface responseInterceptor) {

		if (this.responseInterceptor == null)
			this.responseInterceptor = new ResponseInterceptor2(
					responseInterceptor);

		else
			this.responseInterceptor = new ResponseInterceptor2(
					responseInterceptor)
					.setResponseInterceptor2(this.responseInterceptor);

		return this;
	}

 

	public <T> TaskHandler newRequest2(BaseHttpRequest<T> t,
			HttpCallBack<ResponseBody<T>> httpCallback) {

		// 创建任务
		BaseHttpTask task = new BaseHttpTask(0);

		task.setAsyncHttpClient(new HttpClientImpl());

		// 添加 请求 拦截器
		t.addRequestInterceptor(requestInterceptor);

		// 添加一个监听者
		t.registerCallBack(httpCallback);

		task.setHttprequest(t);

		// 结果观察者
		BaseResultInterceptorObsever<T> resultObsever = new BaseResultInterceptorObsever<T>();

		// 设置拦截器
		resultObsever.setResponseInterceptor2(responseInterceptor);

		// 设置回掉
		resultObsever.setHttpCallBack(httpCallback);

		defaultConfig(t);
	
		// 提交请求
		SyncPoolExecutor.execute(task, resultObsever);

		return task;
	}
	
	
	
	private <T> void defaultConfig(BaseHttpRequest<T> request){
		
		if(request.getRequestMethod()==null){
			request.setRequestMethod(config.getRequestMethod());
		}
		
		if(request.getConnectTimeout()==0){
			request.setConnectTimeout(config.getConnectTimeout());

		}
		if(request.getSocketTimeout()==0){
			request.setSocketTimeout(config.getSocketTimeout());
		}
		
		if(request.getHeaders()!=null&&request.getHeaders().size()==0){
			request.getHeaders().addAll(config.getHeadList());
		}
		
		
		
		
		
	}
	
	

}
