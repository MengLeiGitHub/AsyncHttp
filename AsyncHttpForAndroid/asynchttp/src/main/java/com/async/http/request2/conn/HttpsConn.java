package com.async.http.request2.conn;

import com.async.http.request2.BaseRequest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpsConn extends BaseConn<HttpsURLConnection>{

	public HttpsConn(BaseRequest<?> basereBaseHttpRequest) {
		super(basereBaseHttpRequest);
		// TODO Auto-generated constructor stub
	}

	@Override
	public HttpsURLConnection createConn(URL url,BaseRequest<?>  baseRequest) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	 

	@Override
	public void initRequestProperty(HttpsURLConnection t,
			BaseRequest<?> baseHttpRequest) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void initOtherProperty(HttpsURLConnection t,
			BaseRequest<?> baseHttpRequest) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InputStream getInputStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutputStream getOutputStram() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getResponseCode() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	

}
