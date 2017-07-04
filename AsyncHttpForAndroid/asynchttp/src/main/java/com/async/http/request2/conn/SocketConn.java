package com.async.http.request2.conn;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

import com.async.http.constant.Constents;
import com.async.http.exception.CancledOrInterruptedExcetion;
import com.async.http.exception.HttpException;
import com.async.http.request2.BaseRequest;
import com.async.http.request2.convert.StringDataConvert;
import com.async.http.request2.entity.Header;
import com.async.http.utils.LogUtils;

public class SocketConn extends BaseConn<Socket> {

	public SocketConn(BaseRequest<?> basereBaseHttpRequest) {
		super(basereBaseHttpRequest);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Socket createConn(URL url, BaseRequest<?> baseRequest)
			throws Exception {
		// TODO Auto-generated method stub
		int port = url.getPort();
		InetAddress addr = InetAddress.getByName(url.getHost());
		if (port == -1)
			port = url.getDefaultPort();

		
		Socket socket = new Socket(addr, port);
		socket.setKeepAlive(true);
		socket.setTcpNoDelay(true);
		socket.setSoTimeout(100000);
		int sendsize=(int)baseRequest.getByteCacheSize()==0?5*1024:(int)baseRequest.getByteCacheSize();
		int recesize=(int)baseRequest.getReceCacheSize()==0?5*1024:(int)baseRequest.getReceCacheSize();
			
		socket.setReceiveBufferSize(recesize);
		socket.setSendBufferSize(sendsize);
		
		return socket;
	}

 
	@Override
	public void initRequestProperty(Socket t, BaseRequest<?> baseHttpRequest)
			throws Exception {
		// TODO Auto-generated method stub
		 
		String servletName = realURL.getPath();
		int port = realURL.getPort();
		if (port == -1)
			port = realURL.getDefaultPort();
		String host = realURL.getHost();

		StringBuffer header = new StringBuffer();
		// headre:http请求头信息

		//
		header.append("POST " + servletName + " HTTP/1.1").append("\r\n");
		header.append("Host:" + host + ":" + port + "").append("\r\n");
		header.append("Accept: */*").append( "\r\n");
		header.append("Accept-Language:zh-CN").append("\r\n");

		ArrayList<Header> headlist = baseHttpRequest.getHeaders();

        for (Header head : headlist) {
            if (Constents.CONTENT_TYPE.equals(head.getKey())) {
            	header.append(head.getKey());
            	header.append(":");
            	header.append(head.getVal());

            	header.append(Constents.BOUNDARY_PARAM);
            	header.append(boundaryBuilder.getBoundary());

 
            } else {
            	header.append(head.getKey());
            	header.append(":");
            	header.append(head.getVal());
               
            }
            header.append("\r\n");

        }
		
		
		header.append("Content-Length:")
				.append(baseHttpRequest.getTotalParamLength()).append("\r\n");
		
		if(header.indexOf("Connection")<0&&header.indexOf("connection")<0)
		   header.append("Connection:Keep-Alive").append("\r\n");
			
		header.append("\r\n");
		
		if(LogUtils.isOpenLogHeaders()){
			System.err.println("tag- request header :\n"+header.toString());
		}
		String charset=baseHttpRequest.getDataConverCharset();
		byte[] by=header.toString().getBytes(charset);
		OutputStream out=getOutputStram();
		out.write(by);
		out.flush();
		

	}

	@Override
	public InputStream getInputStream()throws IOException{
		// TODO Auto-generated method stub
		return conn.getInputStream();
	}

	@Override
	public OutputStream getOutputStram() throws IOException{
		// TODO Auto-generated method stub
		return conn.getOutputStream();
	}

	@Override
	public int getResponseCode() throws IOException {
		// TODO Auto-generated method stub
		
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
	 
			byte[] buff = new byte[1024];
			int readLength = 0;
			int l = 0;
			while ( (l = getInputStream().read(buff)) > 0) {
				arrayOutputStream.write(buff, 0, l);
				readLength += l;
			 
			}
		//	return arrayOutputStream.toString(request.getDataConverCharset());
			System.err.println("===");
		System.err.println(arrayOutputStream.toString(getBasereBaseHttpRequest().getDataConverCharset()));
		System.err.println("===");

		
		return 200;
	}

	@Override
	public long getContentLength() {
		// TODO Auto-generated method stub
		return 190;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		if(conn!=null&&!conn.isClosed()){
			conn.close();
		}
		
	}

	@Override
	public void initOtherProperty(Socket t, BaseRequest<?> baseHttpRequest)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
