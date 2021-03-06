package com.async.http.request2;

import com.async.http.client.HttpMethod;
import com.async.http.request2.entity.Header;

import java.util.ArrayList;

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

	/**
	 * baseUrl
	 */

	private String baseUrl;


	private String   defaultConverCharset;


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


	public String getDefaultConverCharset() {
		return defaultConverCharset;
	}

	public void setDefaultConverCharset(String defaultConverCharset) {
		this.defaultConverCharset = defaultConverCharset;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getBaseUrl() {
		return baseUrl;
	}
}
