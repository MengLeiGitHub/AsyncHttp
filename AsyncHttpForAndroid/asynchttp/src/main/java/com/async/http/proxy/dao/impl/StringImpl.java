package com.async.http.proxy.dao.impl;

import com.async.http.constant.Charsets;
import com.async.http.proxy.dao.RequesyTypeInterface;
import com.async.http.proxy.entity.CreatorBeans;
import com.async.http.proxy.entity.ParamBean;
import com.async.http.request2.BaseRequest;
import com.async.http.request2.StringRequest;
import com.async.http.request2.part.StringParamPart;

import java.util.List;

public class StringImpl implements RequesyTypeInterface {

	CreatorBeans creatorBeans;
	public StringImpl(CreatorBeans creatorBeans){
		this.creatorBeans=creatorBeans;
	}
	
	public BaseRequest holdRequest() throws Exception {
		// TODO Auto-generated method stub

		String url = creatorBeans.getUrl();
		StringRequest resReques = new StringRequest(url, Charsets.UTF_8);
		List<ParamBean> params = creatorBeans.getList();
		 if(params!=null)
		for (int i = 0; i < params.size(); i++) {
			ParamBean paramBean = params.get(i);
			if(paramBean.getVal()!=null)
				resReques.addParam(new StringParamPart(paramBean.getKey(),
					paramBean.getVal().toString()));
 		}
		resReques.setRequestMethod(creatorBeans.getHttpMethod());
		return resReques;

	}

}
