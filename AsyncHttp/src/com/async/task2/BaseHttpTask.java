package com.async.task2;

 
import com.async.AsyncHttpClient;
import com.async.ConstructionCenter.TaskLife;
import com.async.ConstructionCenter.TaskWork;
import com.async.Interceptor2.RequestInterceptor2;
import com.async.entity.ResponseBody;
import com.async.exception.HttpException;
import com.async.handler.TaskHandler;
import com.async.request2.BaseHttpRequest;


 
public class BaseHttpTask extends TaskWork implements TaskHandler{

	
	private AsyncHttpClient  asyncHttpClient;
	
 	private BaseHttpRequest httprequest;
	
 	
	private  int taskPriority;
	
	private  boolean   isRunning;
	
	TaskLife tasklife;
	 
	
	 
	 
	public BaseHttpTask(int i) {
		// TODO Auto-generated constructor stub
		super(i);
	}
 
	 

	public void setCurrt(int currt) {
		// TODO Auto-generated method stub
		
	}

	 

	public void setTaskPriority(int priority) {
		// TODO Auto-generated method stub
		 this.taskPriority=priority;
	}

	public int getTaskPriority() {
		// TODO Auto-generated method stub
		return taskPriority;
	}

	public synchronized boolean isRunning() {
		// TODO Auto-generated method stub
		return isRunning;
	}

	public synchronized void setRunning(boolean isruning) {
		// TODO Auto-generated method stub
		isRunning=isruning;
	}

	public synchronized  void stop() {
		// TODO Auto-generated method stub
 		 	if(httprequest!=null&&!httprequest.isCancledOrInterrupted())
 			httprequest.stop();
  	}

	public void start() {
		// TODO Auto-generated method stub
 	}

	public void remove() {
		// TODO Auto-generated method stub
 	}

	public Object run(Object... arg0) {
		// TODO Auto-generated method stub
 		ResponseBody responseBody=null;
		
		if (httprequest != null) {
			
			try {
			if(httprequest.isCancledOrInterrupted()){
				 throw new HttpException("The request was cancelled by the user. "){
					 
				 };
			}
			RequestInterceptor2 requestInterceptor2 = httprequest
					.getRequestInterceptor();
			
				if(requestInterceptor2!=null)
 				httprequest = requestInterceptor2.interceptor(httprequest);
 				
				responseBody = asyncHttpClient.request(httprequest,
						httprequest.getResponse());
				
				return responseBody;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			//	System.out.println("request"+httprequest.getIndex()+"============¥Ì¿≤");
			//	e.printStackTrace();
				
				if(responseBody==null)responseBody=new ResponseBody();
				responseBody.setException(new HttpException(e) {
				});
				return responseBody;
			}
			
			
		}
		responseBody=new ResponseBody();
		responseBody.setException(new HttpException(this.getClass().getName() +"Requset should not be null") {
		});
		return  responseBody;
	}

	
	
	
  
	public AsyncHttpClient getAsyncHttpClient() {
		return asyncHttpClient;
	}

	public void setAsyncHttpClient(AsyncHttpClient asyncHttpClient) {
		this.asyncHttpClient = asyncHttpClient;
	}

	public BaseHttpRequest getHttprequest() {
		return httprequest;
	}

	public void setHttprequest(BaseHttpRequest httprequest) {
		this.httprequest = httprequest;
	}
	
	
	

}
