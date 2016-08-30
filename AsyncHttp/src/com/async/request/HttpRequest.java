package com.async.request;

 

import com.async.Interceptor.RequestInterceptor2;
import com.async.builder.RequestBuilder;
import com.async.callback.HttpCallBack;

 
/**
 * request���أ���  response ����
 * request ����
 * @author ML
 *
 */


public class HttpRequest {

	/**
	 * �ص���������
	 */
	private HttpCallBack<String> httpLifeCycleInterface;

	 

	/**
	 * ������������
	 * 
	 */
	private RequestInterceptor2 requestInterceptor;
	
	/**
	 * 
	 * ���ӹ���
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
	 * ����post����
	 * @param requestBuilder
	 */
	public String sendPost(RequestBuilder requestBuilder) {
		return  request(requestBuilder,new HttpPostRequest(httpLifeCycleInterface));
		
		
	}
 
	/**
	 * ����get����
	 * @param requestBuilder
	 */
	public String sendGet(RequestBuilder requestBuilder) {
		return request(requestBuilder,new HttpGetRequest(httpLifeCycleInterface));
 	}

	/**
	 * ��������
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