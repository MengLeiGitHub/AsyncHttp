package com.async.http.callback;

import com.async.http.entity.ResponseBody;

public  interface UploadProgrossCallback<ResponseBody> extends
		HttpCallBack<ResponseBody> {


	public  void upload_current(long current,long currentFileTotal ,long total);



}
