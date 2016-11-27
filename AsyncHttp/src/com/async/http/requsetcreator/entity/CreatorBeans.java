package com.async.http.requsetcreator.entity;

import java.util.List;
import java.util.Set;

import com.async.http.clientImpl.HttpMethod;

public class CreatorBeans {
	private  String url;
	private  List<ParamBean>  list;
	private  List<AnnotationKey>  keys;
	private  HttpMethod  httpMethod;
	private  boolean    isUpload;
	private  Class  methodClass;
	private  boolean   isDownload;
	
	private  List<MultiPart> multiParts;
	
	private  boolean   isJSONPOST;
	
	
	
	public void setList(List<ParamBean> list) {
		this.list = list;
	}
	public List<ParamBean> getList() {
		return list;
	}
	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}
	public HttpMethod getHttpMethod() {
		return httpMethod;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	public void setKeys(List<AnnotationKey> keys) {
		this.keys = keys;
	}
	public List<AnnotationKey> getKeys() {
		return keys;
	}
	public void setUpload(boolean isUpload) {
		this.isUpload = isUpload;
	}
	public boolean isUpload() {
		return isUpload;
	}
	public void setMethodClass(Class methodClass) {
		this.methodClass = methodClass;
	}
	public Class getMethodClass() {
		return methodClass;
	}
	public void setDownload(boolean isDownload) {
		this.isDownload = isDownload;
	}
	public boolean isDownload() {
		return isDownload;
	}
	public List<MultiPart> getMultiParts() {
		return multiParts;
	}
	public void setMultiParts(List<MultiPart> multiParts) {
		this.multiParts = multiParts;
	}
	public boolean isJSONPOST() {
		return isJSONPOST;
	}
	public void setJSONPOST(boolean isJSONPOST) {
		this.isJSONPOST = isJSONPOST;
	}
	
	
}
