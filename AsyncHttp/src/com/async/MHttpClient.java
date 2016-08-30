package com.async;


import java.util.HashMap;

import com.async.SyncPoolExecutor;
import com.async.Interceptor.RequestInterceptor2;
import com.async.Interceptor.RequestInterceptorActionInterface;
import com.async.Interceptor.ResponseInterceptor2;
import com.async.Interceptor.ResponseInterceptorActionInterface;
import com.async.callback.HttpCallBack;
import com.async.entity.URLConfig;
import com.async.handler.RequestHandler;
import com.async.task.StringHttpTask;
import com.async.task.StringResultInterceptorObsever;



 
 
 
public class MHttpClient {
	RequestInterceptor2 requestInterceptor;
	ResponseInterceptor2 responseInterceptor;

	
	public MHttpClient(){
		requestInterceptor=AsyncHttp.instance().getRequestInterceptor();
		responseInterceptor=AsyncHttp.instance().getResponseInterceptor();
		
	}
	
	
	public MHttpClient addRequestInterceptor(RequestInterceptorActionInterface requestInterceptor) {
		if (this.requestInterceptor == null)
			this.requestInterceptor =new RequestInterceptor2(requestInterceptor); 
 
		else
		this.requestInterceptor = new RequestInterceptor2(requestInterceptor).setRequestInterceptor2(this.requestInterceptor);
 
		
		return this;
	}

	public MHttpClient addResponseInterceptor(
			ResponseInterceptorActionInterface responseInterceptor) {
	 
		
		if (this.responseInterceptor == null)
			this.responseInterceptor =new ResponseInterceptor2(responseInterceptor); 
 
		else
		this.responseInterceptor = new ResponseInterceptor2(responseInterceptor).setResponseInterceptor2(this.responseInterceptor);

		
		
		
		return this;
	}

	public RequestHandler newRequest(URLConfig config,
			HttpCallBack<String>  httpCallback) {

		//创建任务
		StringHttpTask callBack = new StringHttpTask(config, httpCallback);

		//获取 requestHandler
		RequestHandler requstManager = callBack.getRequstManager();
		
		
		//添加 请求 拦截器
		requstManager.addRequestInterceptor(this.requestInterceptor);
		
		//结果观察者
		StringResultInterceptorObsever resultObsever=new StringResultInterceptorObsever();
		
		
		//设置拦截器
		resultObsever.setResponseInterceptor2(responseInterceptor);
		
		//设置回掉
		resultObsever.setHttpCallBack(httpCallback);
		
		//提交请求
		SyncPoolExecutor.execute(callBack, resultObsever);
		
		
		
		return requstManager;
	}

}
