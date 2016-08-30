package com.async.task;

 
import com.async.ConstructionCenter.TaskInterface;
import com.async.ConstructionCenter.TaskLife;
import com.async.callback.HttpCallBack;
import com.async.constant.RequestType;
import com.async.entity.URLConfig;
import com.async.handler.RequestHandler;
import com.async.request.HttpRequest;


 
public class StringHttpTask implements TaskInterface{

	
	private URLConfig config;
	
	private HttpRequest http;
	
	private RequestHandler requstManager;
	
	private  int taskPriority;
	
	private  boolean   isRunning;
	
	TaskLife tasklife;
	
	
	public StringHttpTask(URLConfig config,HttpCallBack<String> httpLifeCycleInterface) {
		this.config = config;
		http=new HttpRequest(httpLifeCycleInterface);
		requstManager=new RequestHandler(config,http);
		
		
	}
	
	public RequestHandler getRequstManager() {
		return requstManager;
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

	public void stop() {
		// TODO Auto-generated method stub
		tasklife.stop();
		http.stop();
	}

	public void start() {
		// TODO Auto-generated method stub
		tasklife.start();
	}

	public void remove() {
		// TODO Auto-generated method stub
		tasklife.remove();
	}

	public Object run(Object... arg0) {
		// TODO Auto-generated method stub
		String result=null;
				switch (config.getRequestType()) {
				case RequestType.POST:
					result= requstManager.SendPost();
					break;
				case RequestType.GET:
					result= requstManager.SendGet();
					break;

				default:
					break;
				}

		
		
		
		return result;
	}

	public void registerObsever(TaskLife arg0) {
		// TODO Auto-generated method stub
		this.tasklife=arg0;
	}

	public TaskLife getTasklife() {
		return tasklife;
	}
	 
}
