package com.async.request;

 

import com.async.Interceptor.RequestInterceptor2;
import com.async.builder.RequestBuilder;
import com.async.callback.HttpCallBack;

 
/**
 * request拦截，和  response 拦截
 * request 请求
 * @author ML
 *
 */


public class HttpRequest {

	/**
	 * 回掉生命周期
	 */
	private HttpCallBack<String> httpLifeCycleInterface;

	 

	/**
	 * 请求处理拦截器
	 * 
	 */
	private RequestInterceptor2 requestInterceptor;
	
	/**
	 * 
	 * 链接管理
	 */
	
	ManagerConnectionInterface managerConnectionInterface;
	
	

	public void addRequestInterceptor(RequestInterceptor2 requestInterceptor) {
		this.requestInterceptor = requestInterceptor;
	}

 

 	public HttpRequest(HttpCallBack<String> httpLifeCycleInterface) {
		this.httpLifeCycleInterface = httpLifeCycleInterface;
	}

 	
	public void stop() {
		managerConnectionInterface.stop();
 		httpLifeCycleInterface.finish();
	}
	
	public void cancle(){
		managerConnectionInterface.cancle();
	}
	
	

	/**
	 * 发送post请求
	 * @param requestBuilder
	 */
	public String sendPost(RequestBuilder requestBuilder) {
		return  request(requestBuilder,new HttpPostRequest(httpLifeCycleInterface));
		
		
	}
 
	/**
	 * 发送get请求
	 * @param requestBuilder
	 */
	public String sendGet(RequestBuilder requestBuilder) {
		return request(requestBuilder,new HttpGetRequest(httpLifeCycleInterface));
 	}

	/**
	 * 请求链接
	 * @param requestBuilder
	 * @param requestInterface
	 * @return
	 */
	private String  request(RequestBuilder requestBuilder,RequestInterface requestInterface){
		try {
			RequestBuilder requestBuiler2 ;
			
			if(requestInterceptor!=null){
				
				requestBuiler2 = requestInterceptor
						.interceptor(requestBuilder);
			}else{
				requestBuiler2=requestBuilder;
			}
		
 			requestInterface.setRequestBuiler2(requestBuiler2);
 			
 			managerConnectionInterface=(ManagerConnectionInterface)requestInterface;
 			
  			String result=requestInterface.request();
   			
 			
			return result;
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
 			httpLifeCycleInterface.fail(e);
 			httpLifeCycleInterface.finish();
		}
		return null;
	}
	
 
}