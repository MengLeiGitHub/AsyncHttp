package com.async.http.request2.conn;

import com.async.http.client.BoundaryBuilder;
import com.async.http.request2.BaseRequest;
import com.async.http.utils.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public abstract class BaseConn<T> {
	
	
	T   conn;

	/**
	 * 基本请求
	 */
	private BaseRequest<?> basereBaseHttpRequest;
	/**
	 * 网络请求唯一标识 工具
	 */
	BoundaryBuilder boundaryBuilder;

	URL realURL;

	public BaseConn(BaseRequest<?> basereBaseHttpRequest) {
		this.basereBaseHttpRequest = basereBaseHttpRequest;
	}

	public void setBoundaryBuilder(BoundaryBuilder boundaryBuilder) {
		this.boundaryBuilder = boundaryBuilder;
	}

	public abstract int getResponseCode() throws IOException ;
	
	/**
	 * 获取内容长度
	 * @return
	 */
	public abstract long  getContentLength();

	/**
	 * 链接
	 */

	public void openConnection() throws Exception {
		realURL = checkURL();
		 conn = createConn(realURL, basereBaseHttpRequest);
		 initRequestProperty(conn, basereBaseHttpRequest);
		 initOtherProperty(conn, basereBaseHttpRequest);
	

	};


	private URL checkURL() throws Exception {	
		// 改写 网络请求部分
		// 0.先连接url
		String url = basereBaseHttpRequest.getUrl();
		if (StringUtils.isNull(url)) {
			
	    	/*throw new HttpException("Request url can not be  empty"){
	    		
	    	};*/
		
		}
		if (!url.startsWith("http") || !url.startsWith("https")) {
			String baseUrl = basereBaseHttpRequest.getBaseUrl();
			if (!url.startsWith("http")) {
				if (StringUtils.isNull(basereBaseHttpRequest.getBaseUrl())) {
					if (!url.startsWith("http") || !url.startsWith("https")) {
					/*	throw new HttpException(
								"Request url  is not  vaild ,please check url format\nerror url:"
										+ basereBaseHttpRequest.getUrl()) {
						};
				*/
					}
				}
				url = baseUrl + url;
			}

		}

		URL realUrl = new URL(url);
		
		
		
		

		return realUrl;
	}

	/**
	 * 创建一个具体链接类
	 * 
	 * @return
	 */

	public abstract T createConn(URL reUrl,BaseRequest<?> basereBaseHttpRequest) throws Exception;

	
	/**
	 * 设置链接其他的属性
	 */

	public  abstract  void initOtherProperty(T t,BaseRequest<?> baseHttpRequest)  throws Exception;

	/**
	 * 初始化http协议请求头
	 */
	public abstract void initRequestProperty(T t, BaseRequest<?> baseHttpRequest)
			throws Exception;

	/**
	 * 获取输入流
	 * 
	 * @return
	 */

	public abstract InputStream getInputStream()  throws IOException ;

	/**
	 * 获取输出流
	 * 
	 * @return
	 */
	public abstract OutputStream getOutputStram()  throws IOException ;

	public abstract void close()   throws IOException;
	
	public BaseRequest<?> getBasereBaseHttpRequest() {
		return basereBaseHttpRequest;
	}

}
