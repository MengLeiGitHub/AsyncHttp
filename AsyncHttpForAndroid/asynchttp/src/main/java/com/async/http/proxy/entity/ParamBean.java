package com.async.http.proxy.entity;

public class ParamBean<T> {

	 private  String   key;
	 private  T        val;
	 
	 public void setKey(String key) {
		this.key = key;
	}
	 public String getKey() {
		return key;
	}
	 public T getVal() {
		return val;
	}
	 public void setVal(T val) {
		this.val = val;
	}
	 
}
