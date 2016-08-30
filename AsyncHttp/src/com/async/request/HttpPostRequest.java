package com.async.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import com.async.builder.RequestBuilder;
import com.async.callback.HttpCallBack;



public class HttpPostRequest implements ManagerConnectionInterface,RequestInterface{
	boolean  isCancle,isStop,isContinue;
	
	
	
	HttpCallBack<String>	httpLifeCycleInterface;
	RequestBuilder requestBuiler2;
	public HttpPostRequest(HttpCallBack<String> httpLifeCycleInterface) {
		// TODO Auto-generated constructor stub
		this.httpLifeCycleInterface=httpLifeCycleInterface;
	}
	
	public String  post() throws Exception{
		String hostUrl=requestBuiler2.getUrl();
		String parpam=requestBuiler2.getConenctionParams();
 		return sendPost(hostUrl,parpam);
 	}
	
	

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public String sendPost(String url, String param) throws Exception {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
 
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			
 			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("contentType", "utf-8");
 			
			
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			if(isStop){
				httpLifeCycleInterface.fail(new Exception("The request cancled by user"));				
				return new String();
			}
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			httpLifeCycleInterface.start();
			
			
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream() ));
			String line;
			while ((line = in.readLine()) != null&&!isStop) {
				result += line;
			};
			
			
		// 使用finally块来关闭输出流、输入流
		 
 				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
		 
 	   	     return result;

		
	}

 	public void stop() {
		// TODO Auto-generated method stub
 		synchronized (this) {
 			isStop=true;
		}
	}

 	public void start() {
		// TODO Auto-generated method stub
		isStop=false;
	}

 	public void continueRun() {
		// TODO Auto-generated method stub
		isContinue=true;
		isStop=false;
		
	}

 	public void cancle() {
		// TODO Auto-generated method stub
		isContinue=false;
		isStop=true;
	}

	public String request() throws Exception {
		// TODO Auto-generated method stub
 		
 		return post();
	}

	public void setRequestBuiler2(RequestBuilder requestBuiler2) {
		// TODO Auto-generated method stub
		this.requestBuiler2=requestBuiler2;
	}
	
	
	
}
