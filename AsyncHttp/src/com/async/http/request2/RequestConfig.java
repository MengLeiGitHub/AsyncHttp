package com.async.http.request2;

import java.util.ArrayList;

import com.async.http.clientImpl.HttpMethod;
import com.async.http.request2.entity.Header;

public class RequestConfig {
	/**
	 * connection   method   (post get put /....)
	 */
	private HttpMethod  RequestMethod;
	 /**
     * connect timeout
     */
    private int connectTimeout = 10000;
    /**
     * socket timeout
     */
    private int socketTimeout = 10000;
	
    /**
     * isUseCache
     */
    private  boolean  isUsecaChe ;
    
    /**
     * cache dir
     */
    
	private  String   CacheDir;
	
	
	/**
	 *  header
	 */
	
	private  ArrayList<Header> headList;


	public HttpMethod getRequestMethod() {
		return RequestMethod;
	}


	public void setRequestMethod(HttpMethod requestMethod) {
		RequestMethod = requestMethod;
	}


	public int getConnectTimeout() {
		return connectTimeout;
	}


	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}


	public int getSocketTimeout() {
		return socketTimeout;
	}


	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}


	public boolean isUsecaChe() {
		return isUsecaChe;
	}


	public void setUsecaChe(boolean isUsecaChe) {
		this.isUsecaChe = isUsecaChe;
	}


	public String getCacheDir() {
		return CacheDir;
	}


	public void setCacheDir(String cacheDir) {
		CacheDir = cacheDir;
	}


	public ArrayList<Header> getHeadList() {
		return headList;
	}


	public void setHeadList(ArrayList<Header> headList) {
		this.headList = headList;
	}
	
	
	
	
	
}
