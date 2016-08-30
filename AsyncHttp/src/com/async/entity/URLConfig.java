package com.async.entity;

import java.util.HashMap;

import com.async.constant.RequestType;


 
public final class URLConfig {
	 private  int  requestType;
	 private String url;
	 private HashMap<String, Object> map;
	 private int   resultType=RequestType.POST;
	 
	 public String getUrl() {
		return url;
	 }
	 public void setUrl(String url) {
		this.url = url;
	 }
	 public HashMap<String, Object> getMap() {
		return map;
	 }
	 public void setMap(HashMap<String, Object> map) {
		this.map = map;
	 }
	 public int getRequestType() {
		return requestType;
	 }
	 public void setRequestType(int requestType) {
		this.requestType = requestType;
	 }
	public int getResultType() {
		return resultType;
	}
	public void setResultType(int resultType) {
		this.resultType = resultType;
	}
	 
	
	
	
	
}
