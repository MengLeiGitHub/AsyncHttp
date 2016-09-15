package com.async.http.request2.entity;



/**
 * 网络请求链接的头部信息
 * @author ml
 *
 */
public class Header {
		
		
	private String key;
	private String val;
	public Header(String key2, String val2) {
		// TODO Auto-generated constructor stub
		this.key=key2;
		this.val=val2;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
}
