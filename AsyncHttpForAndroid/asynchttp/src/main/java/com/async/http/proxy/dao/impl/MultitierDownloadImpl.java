package com.async.http.proxy.dao.impl;

import com.async.http.AsyncHttp;
import com.async.http.proxy.dao.RequesyTypeInterface;
import com.async.http.proxy.entity.CreatorBeans;
import com.async.http.request2.BaseRequest;
import com.async.http.request2.FileRequest;
import com.async.http.request2.download;

public class MultitierDownloadImpl implements RequesyTypeInterface {

	public CreatorBeans creatorBeans;

	public MultitierDownloadImpl(CreatorBeans creatorBeans) {
		this.creatorBeans = creatorBeans;
	}

	public BaseRequest holdRequest() throws  Exception{
		// TODO Auto-generated method stub
		String url = creatorBeans.getUrl();
		String filepath = creatorBeans.getList().get(0).getVal().toString();

		download resReques = new download(url);
		resReques.setFilepath(filepath);
		resReques.setRequestMethod(creatorBeans.getHttpMethod());
		resReques.setRetry(true);
		return resReques;
	}

}
