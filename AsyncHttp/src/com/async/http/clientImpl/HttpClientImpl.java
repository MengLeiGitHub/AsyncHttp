package com.async.http.clientImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.net.ssl.HttpsURLConnection;

import com.async.http.AsyncHttp;
import com.async.http.AsyncHttpClient;
import com.async.http.constant.Constents;
import com.async.http.entity.ResponseBody;
import com.async.http.exception.HttpException;
import com.async.http.request2.BaseHttpRequest;
import com.async.http.request2.FileRequest;
import com.async.http.request2.download;
import com.async.http.request2.entity.Header;
import com.async.http.request2.part.BaseParamPart;
import com.async.http.request2.record.RecordEntity;
import com.async.http.utils.LogUtils;
import com.async.http.utils.StringUtils;
import com.async.http.utils.UrlEncodeUtils;

public class HttpClientImpl implements AsyncHttpClient {

	private BoundaryBuilder boundaryBuilder;

	public <T> ResponseBody<T> request(BaseHttpRequest<T> request,
			ResponseBody<T> responseBody) throws Exception {
		OutputStream outputStream = null;
		InputStream inputStream = null;
		boundaryBuilder = new BoundaryBuilder();

		try {

			// 改写 网络请求部分

			// 0.先连接url
			String url=request.getUrl();
			if(StringUtils.isNull(url)){
					throw new HttpException("Request url can not be  empty") {
					};
			}
			if(!url.startsWith("http")||url.startsWith("https")){
				  throw new  HttpException("Request url  is not  vaild ,please check url format") {
				};
			}
			
			//url=UrlEncodeUtils.encodeUrl(url);
			
 			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection) realUrl
					.openConnection();

			conn.setReadTimeout(request.getSocketTimeout());

			conn.setConnectTimeout(request.getConnectTimeout());

			// 1.设置网络请求的头部信息（方法参数，缓存，assaent ...）
			ArrayList<Header> headlist = request.getHeaders();

			for (Header head : headlist) {
				if (Constents.CONTENT_TYPE.equals(head.getKey())) {
					conn.addRequestProperty(head.getKey(),
							head.getVal() + Constents.BOUNDARY_PARAM
									+ boundaryBuilder.getBoundary());
				} else {
					conn.addRequestProperty(head.getKey(), head.getVal());
				}
				
				LogUtils.e("key="+head.getKey() +"    val="+head.getVal());
				
				
			}

			// 2.判断是否有ssl，if（ishave）配置ssl

			if (request.isHaveSSL()) {
				// 配置ssl
				HttpsURLConnection httpsURLConnection;
				httpsURLConnection = (HttpsURLConnection) realUrl
						.openConnection();
				httpsURLConnection.setDefaultSSLSocketFactory(null);
				// 未完待续

			}
			conn.setInstanceFollowRedirects(true);

			conn.setRequestMethod(request.getRequestMethod().getMethodName());
			LogUtils.e(request.getRequestMethod().getMethodName());

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			if(request instanceof download)
			responseBody.setRecordEntity(((download) request).getRecordEntity());
			
			responseBody.setRequestParamLength(request.getTotalParamLength());

			// 3.发送请求参数，（通过urlconnection outputStream 发送 数据 ）
			/**
			 * 1.可能会有很多数据类型，所以 有 list 数组 循环 output 2.BasePart<T> (chrild string
			 * file 。。。。。。)
			 */
			request.getCallBack().start();
			
			if (request.getRequestMethod() == HttpMethod.Post
					|| request.getRequestMethod() == HttpMethod.Put
					|| request.getRequestMethod() == HttpMethod.Patch) {
				outputStream = conn.getOutputStream();
				
				write(outputStream, request);

				int code=conn.getResponseCode();
 				if(code>=300&&code<600){
 					HttpException httpException= new HttpException("hhh  Server returned HTTP response code: "+code+" for URL:"+request.getUrl()) {
					};
 					responseBody.setException(httpException);
 					
					throw httpException;
 				}
				
 			}
			// 4.设置response
			
			/*
			 * 判断下载的文件是否已经存在如果存在，并且大小和网络文件大小一样，则直接返回
			 */
		
			
			responseBody.setContentLength(conn.getContentLength());

			// 5.获取输入流 （根据类型处理流数据：1.stringConvert 2.fileConvert 3.....）
			/**
			 * 返回的数据类型只能有一种 1。baseConvert(child: 1.string 2. file .....)
			 * 
			 */
			inputStream = conn.getInputStream();
			
			AsyncHttp.instance().logResponseHead(conn);
			
			// BaseDataConvert<T> convert=request.getConvert();
			// T result= convert.convert(request, inputStream ,
			// conn.getContentLengthLong() );

			T result = read(request, inputStream, conn.getContentLength());

			responseBody.setResult(result);

			return responseBody;

		} catch (Exception e) {
			
			throw e;

		} finally {
			try {
				if (outputStream != null)

					outputStream.close();

				if (inputStream != null) {

					inputStream.close();

				}

			} catch (IOException e) {
				
				throw e;

			}
		}

	}

	public void write(OutputStream out, BaseHttpRequest<?> req)
			throws Exception {
		// TODO Auto-generated method stub

		for (BaseParamPart<?> baseParamPart : req.getParamParts()) {

			baseParamPart.setBoundaryStart(boundaryBuilder.getStart_tag());
			baseParamPart.setBoundaryBuilder(boundaryBuilder);
			baseParamPart.createHead();

			baseParamPart.write(out, req);
		}
		if (req.getParamParts() != null && req.getParamParts().size() != 0) {
			out.write(boundaryBuilder.getEnd_tag().getBytes());
			out.flush();
		}
		// out.close();

	}

	public <T> T read(BaseHttpRequest<T> request, InputStream input, long len)
			throws Exception {
		// TODO Auto-generated method stub

		// BaseHttpRequest<T> request,InputStream input,long len,String charset
		
		return request.getConvert().convert(request, input, len);
	}

}
