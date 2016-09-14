package com.async;

import java.io.InputStream;
import java.io.OutputStream;

import com.async.entity.ResponseBody;
import com.async.request2.BaseHttpRequest;


public interface AsyncHttpClient {
 	
	public void  write(OutputStream out,BaseHttpRequest<?> req)throws Exception;
	
	public  <T> T  read(BaseHttpRequest<T> request,InputStream input,long len) throws Exception;

	public  <T>  ResponseBody<T>  request(BaseHttpRequest<T>  request,ResponseBody<T> responseBody) throws Exception;

	
}
