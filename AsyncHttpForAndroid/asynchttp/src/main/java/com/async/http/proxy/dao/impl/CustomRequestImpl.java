package com.async.http.proxy.dao.impl;


import com.async.http.constant.Charsets;
import com.async.http.proxy.dao.RequesyTypeInterface;
import com.async.http.proxy.entity.CreatorBeans;
import com.async.http.proxy.entity.ParamBean;
import com.async.http.proxy.request.ProxyBaseHttpRequest;

import com.async.http.request2.BaseRequest;


import java.lang.reflect.Constructor;
import java.util.List;

public   class CustomRequestImpl implements RequesyTypeInterface {

	CreatorBeans creatorBeans;

	public CustomRequestImpl(CreatorBeans creatorBeans) {
		this.creatorBeans = creatorBeans;
	}

	public BaseRequest holdRequest() throws  Exception{
		// TODO Auto-generated method stub
		ProxyBaseHttpRequest  request;
		String url = creatorBeans.getUrl();
		Class<? extends BaseRequest> cls=creatorBeans.getRequestImpl();
		Constructor constructor=cls.getConstructor(String.class,String.class);
		request=(ProxyBaseHttpRequest) constructor.newInstance(url,Charsets.UTF_8);
	
 

		List<ParamBean> params = creatorBeans.getList();
		request.addParams(params);
		
		
		request.setRequestMethod(creatorBeans.getHttpMethod());
		return request;

	}

}
