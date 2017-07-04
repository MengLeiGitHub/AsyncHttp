package com.async.http.callback;

import com.async.http.entity.ResponseBody;

public  interface DownProgrossCallback<ResponseBody> extends
		HttpCallBack<ResponseBody> {



	public  void download_current(long current, long total);


}
