package com.async.http.request2;

import com.async.http.callback.HttpCallBack;
import com.async.http.entity.ResponseBody;
import com.async.http.request2.conn.BaseConn;
import com.async.http.request2.conn.SocketConn;

public abstract   class BaseSocketRequest<T> extends  BaseRequest<T>{

	public BaseSocketRequest(HttpCallBack<ResponseBody<T>> httpLifeCycleInterface) {
		super(httpLifeCycleInterface);
		// TODO Auto-generated constructor stub
	}

	public BaseSocketRequest(String url) {
		// TODO Auto-generated constructor stub
		super(url);
	}
	
	public BaseSocketRequest(String url,String charcode) {
		super(url,charcode);
	}

	@Override
	public BaseConn<?> getConn() {
		// TODO Auto-generated method stub
		return   new SocketConn(this);
	}

	
	
	
}
