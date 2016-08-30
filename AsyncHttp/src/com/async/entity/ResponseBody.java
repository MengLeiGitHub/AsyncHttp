package com.async.entity;

public class ResponseBody<T> {
	
	private  Throwable throwable;
	private  T  result;
	public Throwable getThrowable() {
		return throwable;
	}
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
	public T getResult() {
		return result;
	}
	public ResponseBody setResult(T result) {
		this.result = result;
		return this;
	}
	
	
	

}
