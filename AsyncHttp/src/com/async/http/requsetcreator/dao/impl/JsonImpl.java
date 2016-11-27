package com.async.http.requsetcreator.dao.impl;

import java.util.List;

import com.async.http.constant.Charsets;
import com.async.http.request2.BaseHttpRequest;
import com.async.http.request2.JSONRequest;
import com.async.http.request2.entity.Header;
import com.async.http.request2.part.StringParamPart;
import com.async.http.requsetcreator.dao.RequesyTypeInterface;
import com.async.http.requsetcreator.entity.CreatorBeans;
import com.async.http.requsetcreator.entity.ParamBean;

public class JsonImpl implements RequesyTypeInterface {

	CreatorBeans creatorBeans;

	public JsonImpl(CreatorBeans creatorBeans) {
		this.creatorBeans = creatorBeans;
	}

	public BaseHttpRequest holdRequest() {
		// TODO Auto-generated method stub
		String url = creatorBeans.getUrl();
		JSONRequest resReques = new JSONRequest(url, Charsets.UTF_8);
		resReques.addHead(new Header("Content-Type",
				"application/json;charset=UTF-8"));

		List<ParamBean> params = creatorBeans.getList();
		for (int i = 0; i < params.size(); i++) {
			ParamBean paramBean = params.get(i);
			resReques.addParam(new StringParamPart(paramBean.getKey(),
					paramBean.getVal().toString()));

		}
		
		return resReques;

	}

}
