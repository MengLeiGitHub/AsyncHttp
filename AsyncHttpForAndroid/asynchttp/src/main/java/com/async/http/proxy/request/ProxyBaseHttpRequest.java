package com.async.http.proxy.request;

import java.util.List;

import com.async.http.proxy.entity.ParamBean;
import com.async.http.request2.BaseHttpRequest;

public abstract class ProxyBaseHttpRequest<T> extends BaseHttpRequest<T> {

	public ProxyBaseHttpRequest(String url, String charcode) {
		super(url, charcode);
		// TODO Auto-generated constructor stub
	}
	
	public abstract  void addParams(List<ParamBean> paramBeans);

}
