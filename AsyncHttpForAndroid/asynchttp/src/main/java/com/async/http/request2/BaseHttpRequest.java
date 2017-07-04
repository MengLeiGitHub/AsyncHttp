package com.async.http.request2;

import com.async.http.callback.HttpCallBack;
import com.async.http.entity.ResponseBody;
import com.async.http.request2.conn.BaseConn;
import com.async.http.request2.conn.HttpConn;
import com.async.http.request2.conn.HttpsConn;

public abstract   class BaseHttpRequest<T> extends  BaseRequest<T>{

	public BaseHttpRequest(HttpCallBack<ResponseBody<T>> httpLifeCycleInterface) {
		super(httpLifeCycleInterface);
		// TODO Auto-generated constructor stub
	}

	public BaseHttpRequest(String url) {
		// TODO Auto-generated constructor stub
		super(url);
	}
	
	public BaseHttpRequest(String url,String charcode) {
		super(url,charcode);
	}

	@Override
	public BaseConn<?> getConn() {
		// TODO Auto-generated method stub
		if(!isHaveSSL())
		return   new HttpConn(this);

		return  new  HttpsConn(this);
	}


}
