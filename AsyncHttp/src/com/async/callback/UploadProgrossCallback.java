package com.async.callback;

import com.async.entity.ResponseBody;

public  abstract class UploadProgrossCallback<ResponseBody>  implements HttpCallBack<ResponseBody>{

	public void start() {
		// TODO Auto-generated method stub
		
	}

	public void current(long current, long total) {
		// TODO Auto-generated method stub
		
	}
	
	public abstract void upload_current(long current, long total);
	

	public void finish() {
		// TODO Auto-generated method stub
		
	}

	public void success(ResponseBody result) {
		// TODO Auto-generated method stub
		
	}

	public void fail(Exception e) {
		// TODO Auto-generated method stub
		
	}

}
