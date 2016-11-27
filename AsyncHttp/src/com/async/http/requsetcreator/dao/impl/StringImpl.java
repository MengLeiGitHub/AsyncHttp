package com.async.http.requsetcreator.dao.impl;

import java.util.List;

import com.async.http.clientImpl.HttpMethod;
import com.async.http.constant.Charsets;
import com.async.http.request2.BaseHttpRequest;
import com.async.http.request2.StringRequest;
import com.async.http.request2.part.StringParamPart;
import com.async.http.requsetcreator.dao.RequesyTypeInterface;
import com.async.http.requsetcreator.entity.CreatorBeans;
import com.async.http.requsetcreator.entity.ParamBean;

public class StringImpl implements RequesyTypeInterface {

	CreatorBeans creatorBeans;
	public  StringImpl(CreatorBeans creatorBeans){
		this.creatorBeans=creatorBeans;
	}
	
	public BaseHttpRequest holdRequest() {
		// TODO Auto-generated method stub

		String url = creatorBeans.getUrl();
		StringRequest resReques = new StringRequest(url, Charsets.UTF_8);
		List<ParamBean> params = creatorBeans.getList();
		 
		for (int i = 0; i < params.size(); i++) {
			ParamBean paramBean = params.get(i);
			resReques.addParam(new StringParamPart(paramBean.getKey(),
					paramBean.getVal().toString()));
 		}
		resReques.setRequestMethod(creatorBeans.getHttpMethod());
		
		return resReques;

	}

}
