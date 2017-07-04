package com.async.http.request2.conn;

import com.async.http.AsyncHttp;
import com.async.http.client.HttpMethod;
import com.async.http.constant.Constents;
import com.async.http.request2.BaseRequest;
import com.async.http.request2.entity.Header;
import com.async.http.utils.LogUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class HttpConn  extends BaseConn<HttpURLConnection>{

	public HttpConn(BaseRequest<?> basereBaseHttpRequest) {
		super(basereBaseHttpRequest);
		// TODO Auto-generated constructor stub
	}

	@Override
	public HttpURLConnection createConn(URL realUrl,BaseRequest<?> baseRequest) throws Exception {
		// TODO Auto-generated method stub
		 HttpURLConnection conn = (HttpURLConnection) realUrl
                 .openConnection();
         conn.setReadTimeout(baseRequest.getSocketTimeout());
         conn.setConnectTimeout(baseRequest.getConnectTimeout());

 	
		
		return conn;
	}

 
	@Override
	public void initRequestProperty(HttpURLConnection conn,
			BaseRequest<?> baseRequest) throws Exception {
		// TODO Auto-generated method stub
		  // 1.设置网络请求的头部信息（方法参数，缓存，assaent ...）
        ArrayList<Header> headlist = baseRequest.getHeaders();

        for (Header head : headlist) {
            if (Constents.CONTENT_TYPE.equals(head.getKey())) {
                conn.addRequestProperty(head.getKey(),
                        head.getVal() + Constents.BOUNDARY_PARAM
                                + boundaryBuilder.getBoundary());
            } else {
                conn.addRequestProperty(head.getKey(), head.getVal());
            }

        }
        
       
		
	}

	
	@Override
	public void initOtherProperty(HttpURLConnection t,
			BaseRequest<?> baseRequest) throws Exception {
		// TODO Auto-generated method stub
		 
     	 t.setInstanceFollowRedirects(true);
     	
         t.setRequestMethod(baseRequest.getRequestMethod().getMethodName());
		
        // 发送POST请求必须设置如下两行
        if (baseRequest.getRequestMethod() != HttpMethod.Get)
            t.setDoOutput(true);
            t.setDoInput(true);
	}
	
	
	
	@Override
	public InputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		InputStream inputStream=conn.getInputStream();
		if(LogUtils.isOpenLogHeaders())
			AsyncHttp.instance().logResponseHead(conn);

		return inputStream;
	}

	@Override
	public OutputStream getOutputStram()throws IOException {
		// TODO Auto-generated method stub
		return super.conn.getOutputStream();
	}

	@Override
	public int getResponseCode() throws IOException {
		// TODO Auto-generated method stub
		return conn.getResponseCode();
	}

	@Override
	public long getContentLength() {
		// TODO Auto-generated method stub
		return conn.getContentLength();
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		if(conn!=null){
			conn.disconnect();
		}
	}



	

}
