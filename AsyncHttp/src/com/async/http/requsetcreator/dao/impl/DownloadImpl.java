package com.async.http.requsetcreator.dao.impl;

import com.async.http.clientImpl.HttpMethod;
import com.async.http.request2.BaseHttpRequest;
import com.async.http.request2.FileRequest;
import com.async.http.requsetcreator.dao.RequesyTypeInterface;
import com.async.http.requsetcreator.entity.CreatorBeans;

public class DownloadImpl implements RequesyTypeInterface {

	public CreatorBeans creatorBeans;

	public DownloadImpl(CreatorBeans creatorBeans) {
		this.creatorBeans = creatorBeans;
	}

	public BaseHttpRequest holdRequest() {
		// TODO Auto-generated method stub
		String url = creatorBeans.getUrl();

		String filepath = creatorBeans.getList().get(0).getVal().toString();

		final FileRequest resReques = new FileRequest(url);
		resReques.setFilepath(filepath);
		resReques.setRequestMethod(HttpMethod.Get);
		resReques.setRetry(true);

		return resReques;
	}

}
