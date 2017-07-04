package com.async.http.proxy.dao.impl;

import android.util.Log;

import com.async.http.constant.Charsets;
import com.async.http.proxy.dao.RequesyTypeInterface;
import com.async.http.proxy.entity.CreatorBeans;
import com.async.http.proxy.entity.ParamBean;
import com.async.http.request2.BaseRequest;
import com.async.http.request2.JSONRequest;
import com.async.http.request2.entity.Header;
import com.async.http.request2.part.JsonParamPart;
import com.async.http.request2.part.StringParamPart;

import java.util.List;

public class JsonImpl implements RequesyTypeInterface {

	CreatorBeans creatorBeans;

	public JsonImpl(CreatorBeans creatorBeans) {
		this.creatorBeans = creatorBeans;
	}

	public BaseRequest holdRequest() throws  Exception{
		// TODO Auto-generated method stub

		String url = creatorBeans.getUrl();
		JSONRequest resReques = new JSONRequest(url, Charsets.UTF_8);
		resReques.addHead(new Header("Content-Type",
				"application/json;charset=UTF-8"));

		List<ParamBean> params = creatorBeans.getList();
		if(params!=null)
		for (int i = 0; i < params.size(); i++) {
			ParamBean paramBean = params.get(i);
			if(paramBean.getVal()!=null)
			resReques.addParam(new JsonParamPart(paramBean.getKey(),
					paramBean.getVal()));
		}
		resReques.setRequestMethod(creatorBeans.getHttpMethod());
		return resReques;

	}

}
