package com.async.http.requsetcreator;

import java.io.File;
import java.util.List;

import test.StringTest;

import com.async.http.AsyncHttp;
import com.async.http.callback.DownProgrossCallback;
import com.async.http.callback.HttpCallBack;
import com.async.http.callback.UploadProgrossCallback;
import com.async.http.clientImpl.HttpMethod;
import com.async.http.constant.Charsets;
import com.async.http.entity.ResponseBody;
import com.async.http.request2.BaseHttpRequest;
import com.async.http.request2.StringRequest;
import com.async.http.request2.part.StringParamPart;
import com.async.http.requsetcreator.dao.RequesyTypeFactory;
import com.async.http.requsetcreator.dao.RequesyTypeInterface;
import com.async.http.requsetcreator.entity.CreatorBeans;
import com.async.http.requsetcreator.entity.ParamBean;
 
public class RunA {
	
	 private CreatorBeans creatorBeans;
	 MIO  mio;
	 StringObserver  observer;
	 BaseHttpRequest  request;
	 
	 public void setCreatorBeans(CreatorBeans creatorBeans) {
		this.creatorBeans = creatorBeans;
	 }
	
	public RunA runInIO(){
			
		RequesyTypeInterface  requesyTypeInterface=new RequesyTypeFactory().getRequestType(creatorBeans);
			
			 
		try {
			request=requesyTypeInterface.holdRequest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
			
		
		return this;
	}
	
	/**
	 * 用于区分时在主线程中回掉还是在io线程中回掉
	 * @param mio
	 */
	
	public StringObserver  ResultMonitor(MIO mio){
 		this.mio=mio;
 		if(creatorBeans.isJSONPOST())
 		observer=this.new StringObserver(this);
 		if(creatorBeans.isDownload()){
 	 		observer=this.new StringObserver(this);

 		}
 		
 		if(creatorBeans.isUpload ()){
 	 		observer=this.new StringObserver(this);

 		}
 		
 		
 		return observer;
 		
 		
 	}
	private void  StringStart(){
	//	AsyncHttp.instance().stringRequest(request, observer.getCallback());
		
	}
	
	private void uploadStart(){
		//	AsyncHttp.instance().UploadRequest(request, observer.getCallback());
		
	}
	
	private void downloadStart(){
		//	AsyncHttp.instance().download(request, observer.getCallback());
		
	}
	
	
	public class StringObserver{
		RunA runA;
		protected  StringObserver(RunA runA){
			this.runA=runA;
		}
		
		HttpCallBack<ResponseBody<String>> callback;
		public void  Observation(HttpCallBack<ResponseBody<String>> callback){
			this.callback=callback;
			runA.StringStart();
		}
		public HttpCallBack<ResponseBody<String>> getCallback() {
			return callback;
		}
	}
	
	
	public class UploadObserver{
		RunA runA;
		protected  UploadObserver(RunA runA){
			this.runA=runA;
		}
		
		UploadProgrossCallback<ResponseBody<String>> callback;
		public void  Observation(UploadProgrossCallback<ResponseBody<String>> callback){
			this.callback=callback;
			runA.uploadStart();
		}
		public UploadProgrossCallback<ResponseBody<String>> getCallback() {
			return callback;
		}
	}

	public class DownloadObserver{
		RunA runA;
		protected  DownloadObserver(RunA runA){
			this.runA=runA;
		}
		
		DownProgrossCallback<ResponseBody<File>>   callback;
		public void  Observation(DownProgrossCallback<ResponseBody<File>> callback){
			this.callback=callback;
			runA.downloadStart();
		}
		public DownProgrossCallback<ResponseBody<File>> getCallback() {
			return callback;
		}
	}
}
