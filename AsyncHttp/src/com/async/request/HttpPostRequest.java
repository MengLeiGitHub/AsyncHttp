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
	 * ��ָ�� URL ����POST����������
	 * 
	 * @param url
	 *            ��������� URL
	 * @param param
	 *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
	 * @return ������Զ����Դ����Ӧ���
	 */
	public String sendPost(String url, String param) throws Exception {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
 
			URL realUrl = new URL(url);
			// �򿪺�URL֮�������
			URLConnection conn = realUrl.openConnection();
			// ����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			
 			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("contentType", "utf-8");
 			
			
			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// ��ȡURLConnection�����Ӧ�������
			out = new PrintWriter(conn.getOutputStream());
			if(isStop){
				httpLifeCycleInterface.fail(new Exception("The request cancled by user"));				
				return new String();
			}
			// �����������
			out.print(param);
			// flush������Ļ���
			out.flush();
			httpLifeCycleInterface.start();
			
			
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream() ));
			String line;
			while ((line = in.readLine()) != null&&!isStop) {
				result += line;
			};
			
			
		// ʹ��finally�����ر��������������
		 
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
