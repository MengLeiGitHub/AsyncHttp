package com.async.http.callback;

import com.async.http.entity.ResponseBody;

public abstract class DownProgrossCallback<ResponseBody> implements
		HttpCallBack<ResponseBody> {

	public void start() {
		// TODO Auto-generated method stub

	}

	public void current(long current, long total) {
		// TODO Auto-generated method stub

	}

	public abstract void download_current(long current, long total);

	public void finish() {
		// TODO Auto-generated method stub

	}

	public void success(ResponseBody result) {
		// TODO Auto-generated method stub

	}

	public void fail(Exception e,ResponseBody responseBody) {
		// TODO Auto-generated method stub

	}

}
