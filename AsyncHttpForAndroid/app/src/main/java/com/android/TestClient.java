package com.android;
import com.AsyncHttpForAndroid.UploadRequestResultCallBack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		//uploadFile("127.0.0.1", 8080, "E:/dd.pdf", "/HttpProtot/servlet/aa.servlet", "fileName", "sss.bin", "7d00013333");
	}
	/**
	 *
	 *
	 * */
	public static  void uploadFile(String host, Integer port, String fileAbsoPath, String servletName, String fileparaName, String fileName, String boundary, UploadRequestResultCallBack uploadRequestResultCallBack) {
		FileInputStream fin = null;
		OutputStream out = null;
		InputStream in  = null;
		try{
			Socket socket = new Socket(host,port);
			socket.setTcpNoDelay(true);
			socket.setKeepAlive(true);
			socket.setSoTimeout(100000000);
			File f = new File(fileAbsoPath);//需要上传的文件
			fin = new FileInputStream(f);
			StringBuffer fileBefore = new StringBuffer();
			///fileBefore：文件上传的头信息
			fileBefore.append("--").append(boundary).append("\r\n");
			fileBefore.append("Content-Disposition:form-data; name=\""+fileparaName+"\"; filename=\""+fileName+"\"").append("\r\n");
			fileBefore.append("Content-Type: application/octet-stream").append("\r\n");
			fileBefore.append("\r\n");
			//文件上传完之后的尾部信息
			String end = new String("\r\n--"+boundary+"--");
			//参数长度
			long length=f.length()+fileBefore.toString().getBytes().length+end.getBytes().length;
			out = socket.getOutputStream();
			StringBuffer  header = new StringBuffer();
			//headre:http请求头信息

			//
			header.append("POST "+servletName+" HTTP/1.1").append("\r\n");
			header.append("Host:"+host+":"+port+"").append("\r\n");
			header.append("Accept:text/html, application/xhtml+xml, */*").append("\r\n");
			header.append("Accept-Language:zh-CN").append("\r\n");
			header.append("Content-Type:multipart/form-data; boundary="+boundary).append("\r\n");
			header.append("Content-Length:").append(length).append("\r\n");
			header.append("Connection:Keep-Alive").append("\r\n");
			header.append("\r\n");
			//报文结构:

			//第一部分：组装http头部信息
			out.write(header.toString().getBytes());
			//第二部分：组装文件头部信息
			out.write(fileBefore.toString().getBytes());
			//第三部分：组装文件信息
			byte b[]=new byte[1024];
			int rb = 0;
			long current=0;
			long currentFileLength=f.length();
			uploadRequestResultCallBack.start();

			while((rb = fin.read(b))!=-1){
				out.write(b,0,rb);
				current+=rb;
				uploadRequestResultCallBack.upload_current(current,currentFileLength,currentFileLength);
			};
			fin.close();
			//组装文件尾部信息
			out.write(end.getBytes());
			out.flush();
			//服务器返回信息
			in  = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line =null;
			while((line=br.readLine())!=null){
				System.out.println(line);
			}
			br.close();
			uploadRequestResultCallBack.finish();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (fin != null) {
					fin.close();
				}
			} catch (Exception ee) {
				ee.printStackTrace();
			}
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception ee) {
				ee.printStackTrace();
			}
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}

	}

}
