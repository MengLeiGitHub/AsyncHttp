package com.async.http.client;

import java.io.InputStream;
import java.io.OutputStream;

import com.async.http.entity.ResponseBody;
import com.async.http.request2.BaseRequest;


public interface AsyncHttpClient {
 	
	public void  write(OutputStream out, BaseRequest<?> req)throws Exception;
	
	public  <T> T  read(BaseRequest<T> request, InputStream input, long len) throws Exception;

	public  <T>  ResponseBody<T>  request(BaseRequest<T> request, ResponseBody<T> responseBody) throws Exception;

	
}
