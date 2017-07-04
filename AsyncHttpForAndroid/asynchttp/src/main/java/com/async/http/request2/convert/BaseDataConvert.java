package com.async.http.request2.convert;

import java.io.IOException;
import java.io.InputStream;

import com.async.http.exception.HttpException;
import com.async.http.request2.BaseRequest;

public abstract class BaseDataConvert<T> {
	
 
	protected T data;

	public T convert(BaseRequest<T> request, InputStream input, long len) throws IOException, HttpException {

		this.data = read(request,input,len);
		
		input.close();

		return this.data;
	}

	public abstract T read(BaseRequest<T> request, InputStream input, long len) throws IOException, HttpException;

}
