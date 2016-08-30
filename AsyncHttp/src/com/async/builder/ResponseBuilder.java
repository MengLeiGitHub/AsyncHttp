package com.async.builder;

import com.async.entity.ResponseBody;

 
public class ResponseBuilder<T> {

	private ResponseBody<T>  responseBody;
	
	public   ResponseBuilder<T>  build(){
		
		return this;
	}
	public  ResponseBuilder<T>  setResult(T arg0){
		if(responseBody==null)
		responseBody=new ResponseBody<T>();
		responseBody.setResult(arg0);
		return this;
	}
	public ResponseBody<T> response() {
		// TODO Auto-generated method stub
		return responseBody;
	}
	
}
