package com.async;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.async.SyncPoolExecutor;

import com.async.Interceptor2.RequestInterceptor2;
import com.async.Interceptor2.RequestInterceptorActionInterface;
import com.async.Interceptor2.ResponseInterceptor2;
import com.async.Interceptor2.ResponseInterceptorActionInterface;
import com.async.RecordCenter.RecordManager;
import com.async.callback.DownProgrossCallback;
import com.async.callback.HttpCallBack;
import com.async.clientImpl.HttpClientImpl;
import com.async.clientImpl.HttpMethod;
import com.async.entity.ResponseBody;
import com.async.handler.TaskHandler;
import com.async.request2.BaseHttpRequest;
import com.async.request2.RequestConfig;
import com.async.request2.StringRequest;
import com.async.request2.download;
import com.async.request2.entity.Header;
import com.async.request2.record.RecordEntity;
import com.async.task2.BaseHttpTask;
import com.async.task2.BaseResultInterceptorObsever;
import com.async.utils.LogUtils;

public class AsyncHttp {
	RequestInterceptor2 requestInterceptor;
	ResponseInterceptor2 responseInterceptor;

	private RequestConfig config;

	public void setConfig(RequestConfig config) {
		this.config = config;
	}

	public RequestConfig getConfig() {
		return config;
	}

	public AsyncHttp() {
		SyncPoolExecutor.newFixedThreadPool(4, 1, null).isDebug(false);
 		
	}

	private static AsyncHttp mHttpClient;

	public static AsyncHttp instance() {
		if (mHttpClient == null)
			mHttpClient = new AsyncHttp();
		return mHttpClient;
	}

	public AsyncHttp addRequestInterceptor(
			RequestInterceptorActionInterface requestInterceptor) {
		if (this.requestInterceptor == null)
			this.requestInterceptor = new RequestInterceptor2(
					requestInterceptor);

		else
			this.requestInterceptor = new RequestInterceptor2(
					requestInterceptor)
					.setRequestInterceptor2(this.requestInterceptor);

		return this;
	}

	public AsyncHttp addResponseInterceptor(
			ResponseInterceptorActionInterface responseInterceptor) {

		if (this.responseInterceptor == null)
			this.responseInterceptor = new ResponseInterceptor2(
					responseInterceptor);

		else
			this.responseInterceptor = new ResponseInterceptor2(
					responseInterceptor)
					.setResponseInterceptor2(this.responseInterceptor);

		return this;
	}

	public <T> TaskHandler newRequest2(BaseHttpRequest<T> t,
			HttpCallBack<ResponseBody<T>> httpCallback) {

		// 创建任务
		BaseHttpTask task = new BaseHttpTask(0);

		task.setAsyncHttpClient(new HttpClientImpl());

		// 添加 请求 拦截器
		t.addRequestInterceptor(requestInterceptor);

		// 添加一个监听者
		t.registerCallBack(httpCallback);

		task.setHttprequest(t);

		// 结果观察者
		BaseResultInterceptorObsever<T> resultObsever = new BaseResultInterceptorObsever<T>();

		// 设置拦截器
		resultObsever.setResponseInterceptor2(responseInterceptor);

		// 设置回掉
		resultObsever.setHttpCallBack(httpCallback);

		defaultConfig(t);

		// 提交请求
		SyncPoolExecutor.execute(task, resultObsever);
		
		return task;
	}

	public <T> TaskHandler download(final BaseHttpRequest<T> t,
			final HttpCallBack<ResponseBody<T>> httpCallback) {
		long contentlength = 0;
		try {
			URL url = new URL(t.getUrl());
			URLConnection con = url.openConnection();
			contentlength = con.getContentLengthLong();
 		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final long contentlength1=contentlength;
		int num = contentlength>1024*1024*4?3:1;
		long everyLength = contentlength / num;
		long lastend1 = 0;
		final DownProgrossCallback downProgrossCallback = (DownProgrossCallback) httpCallback;

		DownProgrossCallback d = new DownProgrossCallback<ResponseBody>() {
  			 
		       AtomicLong atomic = new AtomicLong(0);  

			@Override
			public   void download_current(long current, long total) {
				// TODO Auto-generated method stub
				atomic.addAndGet(current);  
				downProgrossCallback.download_current(atomic.get(), contentlength1);
			}
			@Override
			public void success(ResponseBody result) {
				// TODO Auto-generated method stub
				super.success(result);
				if(atomic.get()==contentlength1){
					downProgrossCallback.success(result);
				}
				
			}

			@Override
			public void fail(Exception e,ResponseBody response) {
				// TODO Auto-generated method stub
				super.fail(e,response);
				httpCallback.fail(e, response);
				e.printStackTrace();
				((download)t).setRecordEntity(response.getRecordEntity());
				retryDownload( e, t,  httpCallback);
				
				
				/*com.async.request2.record.RecordManager.Call().setCaChepath("E://asynctest");
				
				com.async.request2.record.RecordManager.Call().addErrorTask(((download)t).getRecordEntity());
				*/
				
 			}

			@Override
			public void start() {
				// TODO Auto-generated method stub
				super.start();
				httpCallback.start();
			}

			@Override
			public void finish() {
				// TODO Auto-generated method stub
				super.finish();
				httpCallback.finish();
			}

		};

		final ArrayList<TaskHandler> taskhandlerlist = new ArrayList<TaskHandler>();
          
		for (int i = 0; i < num; i++) {

			long start1 = lastend1 != 0 ? lastend1 + 1 : (i * everyLength);

			long end1 = start1 + everyLength > contentlength ? contentlength
					: (start1 + everyLength);

			lastend1 = end1;
			download  downloadn=new download(t.getUrl());
			RecordEntity recordEntity=new RecordEntity(); 
			if(t instanceof download){
				recordEntity.setStartTag(start1);
				recordEntity.setTotalNum(num);// 共有一个线程
				recordEntity.setEndTag(end1);
				recordEntity.setDownLoadIndex(i);
				recordEntity.setFilePath(((download) t).getRecordEntity().getFilePath());
				downloadn.setTaskPriority(t.getTaskPriority());
				downloadn.setRecordEntity(recordEntity);
				((download) t).setRecordEntity(recordEntity);
			}
  
			downloadn.setRequestMethod(HttpMethod.Get);
			//System.out.println("start1="+start1+"  ===== end1="+end1);
			downloadn.addHead(new Header("RANGE", "bytes=" + start1 + "-" + end1));
			//t.addHead(new Header("Range", "bytes=" + start1 + "-" + end1));

			taskhandlerlist.add(newRequest2(downloadn, d));

		}

		TaskHandler taskHandler = new TaskHandler() {

			public void stop() {
				// TODO Auto-generated method stub
				for (TaskHandler t : taskhandlerlist) {
					synchronized (t) {
						t.stop();
						try {
							t.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						t.notifyAll();
					}

				}
			}

		};

		return taskHandler;
	}

	
 
	
	
	
	
	
	
	protected <T>  void retryDownload(Exception e,final BaseHttpRequest<T> t,
			final HttpCallBack<ResponseBody<T>> httpCallback) {
		// TODO Auto-generated method stub
		
	//	if(e instanceof  SocketTimeoutException){
			if(t instanceof download){
				RecordEntity recordEntity=((download) t).getRecordEntity();
				int i=recordEntity.getTryNums();
				if(i<0)return ;
				else{
					recordEntity.setTryNums(i);
					((download) t).addHead(new Header("RANGE", "bytes=" + recordEntity.getCurrent() + "-" + recordEntity.getEndTag()));
					 newRequest2(t, httpCallback);
				}
				
				
			}
		
			
	//	}
  	}

	private <T> void defaultConfig(BaseHttpRequest<T> request) {

		if (request.getRequestMethod() == null) {
			request.setRequestMethod(config.getRequestMethod());
		}

		if (request.getConnectTimeout() == 0) {
			request.setConnectTimeout(config.getConnectTimeout());

		}
		if (request.getSocketTimeout() == 0) {
			request.setSocketTimeout(config.getSocketTimeout());
		}

		if (request.getHeaders() != null && request.getHeaders().size() == 0) {
			request.getHeaders().addAll(config.getHeadList());
		}

	}

	// 打印回应的头信息
	public void logResponseHead(URLConnection con) {
		
	

		for (int i = 1;; i++) {
			String header = con.getHeaderFieldKey(i);
			if (header != null)
				// responseHeaders.put(header,httpConnection.getHeaderField(header));
				LogUtils.e (header + " : " + con.getHeaderField(header));
			else
				break;
		}
		
	 

	}

}
