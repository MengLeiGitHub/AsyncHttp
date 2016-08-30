package com.async.request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.async.builder.RequestBuilder;
import com.async.callback.HttpCallBack;


public class HttpGetRequest implements ManagerConnectionInterface,RequestInterface{
	
	HttpCallBack<String>	httpLifeCycleInterface;
  	RequestBuilder requestBuilder2;

	boolean  isStop;
	
	public HttpGetRequest(HttpCallBack<String>	httpLifeCycleInterface ){
		this.httpLifeCycleInterface=httpLifeCycleInterface;
		 
	}
	
	public void setRequestBuiler2(RequestBuilder requestBuiler2) {
		this.requestBuilder2 = requestBuiler2;
	}
	
	
	

	private HttpURLConnection OpenConnect(String url, String param) {
		try {

			String urlNameString = url  + param+"&useUnicode=true&characterEncoding=gb2312";
			System.out.println(urlNameString);
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			HttpURLConnection  connection = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			
 			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setRequestProperty("contentType", "utf-8");
			connection.setRequestMethod("GET");
			
			
			// 建立实际的连接
			connection.connect();
			httpLifeCycleInterface.start();

			return connection;
		} catch (Exception e) {
			httpLifeCycleInterface.fail(e);
			return null;
		}
	}
	
	
	
	
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public String sendGet( ) {
		StringBuffer result = new StringBuffer();
		BufferedReader in = null;
		boolean isSuccess = false;
		try {

			if (isStop) {
				httpLifeCycleInterface.fail(new Exception(
						"The user canceled the request"));
				return null;
			}
			
			String url=requestBuilder2.getUrl();
			String param=requestBuilder2.getConenctionParams();
 
			
			URLConnection connection = OpenConnect(url, param);

			 
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null && !isStop) {
				result.append(line);
			}

			if (isStop) {
				isSuccess = false;
			} else {
				isSuccess = true;
			}
				
 
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			httpLifeCycleInterface.fail(e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}

			} catch (Exception e2) {
				httpLifeCycleInterface.fail(e2);
				e2.printStackTrace();
			}
			httpLifeCycleInterface.finish();

		}
		String results = null;
		try {
			results = new String(result.toString().getBytes("GBK"),"utf-8").toString();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
 			return results;
		 
	}


 	public void stop() {
		// TODO Auto-generated method stub
		
	}


 	public void start() {
		// TODO Auto-generated method stub
		
	}


 	public void continueRun() {
		// TODO Auto-generated method stub
		
	}


 	public void cancle() {
		// TODO Auto-generated method stub
		
	}

	public String request() throws Exception {
		// TODO Auto-generated method stub
		return sendGet();
	}

	
	
	
	
	
}
