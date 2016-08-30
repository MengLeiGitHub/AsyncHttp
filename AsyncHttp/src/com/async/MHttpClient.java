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

		//��������
		StringHttpTask callBack = new StringHttpTask(config, httpCallback);

		//��ȡ requestHandler
		RequestHandler requstManager = callBack.getRequstManager();
		
		
		//��� ���� ������
		requstManager.addRequestInterceptor(this.requestInterceptor);
		
		//����۲���
		StringResultInterceptorObsever resultObsever=new StringResultInterceptorObsever();
		
		
		//����������
		resultObsever.setResponseInterceptor2(responseInterceptor);
		
		//���ûص�
		resultObsever.setHttpCallBack(httpCallback);
		
		//�ύ����
		SyncPoolExecutor.execute(callBack, resultObsever);
		
		
		
		return requstManager;
	}

}
