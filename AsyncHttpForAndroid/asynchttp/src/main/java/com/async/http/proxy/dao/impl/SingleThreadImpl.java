package com.async.http.proxy.dao.impl;

import com.async.http.proxy.dao.RequesyTypeInterface;
import com.async.http.proxy.entity.CreatorBeans;
import com.async.http.request2.BaseRequest;
import com.async.http.request2.FileRequest;
/**
 * Created by admin on 2017-03-31.
 */

public class SingleThreadImpl implements RequesyTypeInterface {
    public CreatorBeans creatorBeans;

    public SingleThreadImpl(CreatorBeans creatorBeans) {
        this.creatorBeans = creatorBeans;
    }
    @Override
    public BaseRequest holdRequest() throws Exception {
        String url = creatorBeans.getUrl();
        String filepath = creatorBeans.getList().get(0).getVal().toString();
        final FileRequest resReques = new FileRequest(url);
        resReques.setFilepath(filepath);
        resReques.setRequestMethod(creatorBeans.getHttpMethod());
        resReques.setRetry(true);
        return resReques;
    }
}
