package com.async.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.omg.CORBA.DATA_CONVERSION;

import com.alibaba.fastjson.JSON;
import com.async.http.Interceptor2.RequestInterceptor2;
import com.async.http.Interceptor2.RequestInterceptorActionInterface;
import com.async.http.Interceptor2.ResponseInterceptor2;
import com.async.http.Interceptor2.ResponseInterceptorActionInterface;
import com.async.http.callback.DownProgrossCallback;
import com.async.http.callback.HttpCallBack;
import com.async.http.clientImpl.HttpClientImpl;
import com.async.http.clientImpl.HttpMethod;
import com.async.http.entity.ResponseBody;
import com.async.http.exception.CancledOrInterruptedExcetion;
import com.async.http.exception.HttpException;
import com.async.http.handler.TaskHandler;
import com.async.http.request2.BaseHttpRequest;
import com.async.http.request2.FileRequest;
import com.async.http.request2.RequestConfig;
import com.async.http.request2.StringRequest;
import com.async.http.request2.download;
import com.async.http.request2.entity.Header;
import com.async.http.request2.record.RecordEntity;
import com.async.http.request2.record.RecordEntity.RecordType;
import com.async.http.task2.BaseHttpTask;
import com.async.http.task2.BaseResultInterceptorObsever;
import com.async.http.utils.DeleteDirectory;
import com.async.http.utils.LogUtils;
import com.async.http.utils.StringUtils;
import com.async.pool.SyncPoolExecutor;
import com.async.pool.ConstructionCenter.TaskPriority;
import com.async.pool.ConstructionCenter.TaskWork;
import com.sun.javafx.tk.Toolkit.Task;

public class AsyncHttp {
	RequestInterceptor2 requestInterceptor;
	ResponseInterceptor2 responseInterceptor;

	private RequestConfig config;

	private HashMap<String, HttpCallBack<ResponseBody>> listcallback = null;

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

		// ��������
		BaseHttpTask task = new BaseHttpTask(0);

		task.setAsyncHttpClient(new HttpClientImpl());

		// ��� ���� ������
		t.addRequestInterceptor(requestInterceptor);

		// ���һ��������
		t.registerCallBack(httpCallback);

		task.setHttprequest(t);

		// ����۲���
		BaseResultInterceptorObsever<T> resultObsever = new BaseResultInterceptorObsever<T>();

		// ����������
		resultObsever.setResponseInterceptor2(responseInterceptor);

		// ���ûص�
		resultObsever.setHttpCallBack(httpCallback);

		defaultConfig(t);

		task.setTaskPriority(t.getTaskPriority());
		
		// �ύ����
		int mid = SyncPoolExecutor.execute(task, resultObsever);

		task.setCurrt(mid);

		return task;
	}

	
	 /**
	  * StringRequest ����
	  * 
	  * @param t
	  * @param httpCallback
	  * @return
	  */
	public <T> TaskHandler stringRequest(StringRequest t,
			HttpCallBack<ResponseBody<String>> httpCallback) {
		 
		t.setTaskPriority(TaskPriority.HIGHEST.getValue());
		
 		return newRequest2(t, httpCallback);
	}
	
	
	
	
	
	
	
	/**
	 * ���� �µĶ��߳�����
	 * 
	 * @param t
	 * @param httpCallback
	 * @return
	 */

	public <T> TaskHandler download(final download t,
			final DownProgrossCallback<ResponseBody<File>> httpCallback) {
		String urlString = t.getRecordEntity().getUrl();
		String filepath = t.getRecordEntity().getFilePath();
		if (StringUtils.isNull(urlString)) {
			httpCallback.fail(new HttpException("url can not be null") {
			}, t.getResponse());
			return null;
		}
		if (StringUtils.isNull(filepath)) {
			httpCallback
					.fail(new HttpException(
							"need to specify the path of the file to be downloaded. example: \n  	   		download s=	 new download(new RecordEntity(url,filepath)") {
					}, t.getResponse());
			return null;
		}

		long contentlength = 0;
		try {
			URL url = new URL(t.getUrl());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			contentlength = con.getContentLength();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ����Ƿ�Ϊ�ϵ�����
		// ����1 ������file path �ж��Ƿ��� file
		// ���� 2���ж��Ƿ��� �ļ� file �������ȡ�ļ��������Ա� ��ͬ ���� �ɹ� ��ͬ ���е�����
		// 3�� �ж��Ƿ��� �����ļ�����ͬ���ļ��� �� ��¼�ļ� �� ��ȡ û�� ����û���ļ�����������

		File downloadFile = new File(filepath);
		if (downloadFile.exists()) {

			
			String refile = t.getRecordEntity().getRecordPath();
			File ref = new File(refile);
			File refParent = ref.getParentFile();
			
			System.out.println("============================================================================="+t.getUrl());
			if (refParent.length() == 0) {
  				
				if (downloadFile.length() == contentlength) {
					httpCallback.start();
					ResponseBody<File> result = new ResponseBody<File>();
					result.setResult(downloadFile);
					httpCallback.start();
					httpCallback.success(result);
					httpCallback.finish();
					return null;
				} else {
					
					

				}
 				
 			} else {
				// ��ʾ �� ��¼������
				File[] files = refParent.listFiles();
				t.getRecordEntity().setFilelength(contentlength);
				return breakpointDownload(files, t, httpCallback);

			}
			
			
			
			
			
			

		}
		final long contentlength1 = contentlength;
		int num = contentlength > 1024 * 1024 * 4 ? 4 : 2;
		long everyLength = contentlength / num;
		long lastend1 = 0;
		final DownProgrossCallback downProgrossCallback = (DownProgrossCallback) httpCallback;

		DownProgrossCallback d = (DownProgrossCallback) getCallBack(t,
				httpCallback);

		final ArrayList<TaskHandler> taskhandlerlist = new ArrayList<TaskHandler>();

		for (int i = 0; i < num; i++) {

			long start1 = lastend1 != 0 ? lastend1 + 1 : (i * everyLength);

			long end1 = start1 + everyLength > contentlength ? contentlength
					: (start1 + everyLength);

			lastend1 = end1;
			download downloadn = new download(t.getUrl());
			RecordEntity recordEntity = new RecordEntity();

			recordEntity.setStartTag(start1);
			recordEntity.setUrl(t.getUrl());
			recordEntity.setTotalNum(num);// ���м����߳�
			recordEntity.setEndTag(end1);
			recordEntity.setDownLoadIndex(i);
			recordEntity.setFilePath(t.getRecordEntity().getFilePath());
			recordEntity.setFilelength(contentlength);
			downloadn.setTaskPriority(t.getTaskPriority());
			downloadn.setRecordEntity(recordEntity);
			t.getRecordEntity().setFilelength(contentlength1);
 			downloadn.setRequestMethod(HttpMethod.Get);

 			if(t.getTaskPriority()!=0){
 				
  			}else{
 				downloadn.setTaskPriority(TaskPriority.LOWEST.getValue());
 			}
 			
  			
 			try {
				RandomAccessFile randomAccessFile=new RandomAccessFile(recordEntity.getRecordPath(),"rw");
				randomAccessFile.seek(0);
				
				randomAccessFile.write(JSON.toJSONString(recordEntity).getBytes("utf-8"));
				randomAccessFile.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 			
			downloadn.addHead(new Header("RANGE", "bytes=" + start1 + "-"
					+ end1));
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

	/**
	 * ��Ӳ�̶�ȡδ��ɵ����񣬽�������
	 * 
	 * @param files
	 * @param t
	 * @param httpCallback
	 * @return
	 */

	private TaskHandler breakpointDownload(File[] files,
			com.async.http.request2.download t,
			DownProgrossCallback<ResponseBody<File>> httpCallback) {
		// TODO Auto-generated method stub

		
		
		
		ArrayList<RecordEntity> recordEntitylist=new ArrayList<RecordEntity>();
		long  totalNODown=0;
		long  total=t.getRecordEntity().getFilelength();
		
		//��ȡ���ж���δ���ص�
		for (File file : files) {
			InputStreamReader read = null;
			BufferedReader fileReader = null;
			try {
				read = new InputStreamReader(

				new FileInputStream(file), "utf-8");// ���ǵ������ʽ
				fileReader = new BufferedReader(read);
				String re = fileReader.readLine();
				RecordEntity recordEntity = JSON.parseObject(re,
						RecordEntity.class);
				
				totalNODown+=(recordEntity.getEndTag()-recordEntity.getStartTag()-recordEntity.getCurrent()+1);//+1��ʾ���������ص���һλ��ʼ
				recordEntity.setTotalNum(files.length);
				recordEntitylist.add(recordEntity);
 				fileReader.close();
 				read.close();
 			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
 						if (fileReader == null) {
							fileReader.close();
						}
						if (read == null) {
							read.close();
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		
		// ���õ�ǰ �ܵ�������ɵ��� ���ڳ�ʼ�� callbackʱ ��ֵ��
		
		t.getRecordEntity().setTotalDownloaded(total-totalNODown);
		
		
		DownProgrossCallback d = (DownProgrossCallback) getCallBack(t,
				httpCallback);

		final ArrayList<TaskHandler> taskhandlerlist = new ArrayList<TaskHandler>();

		System.out.println("===========================================");
		
		
		
		
		/*
		 * ���´�������
		 */
		for(RecordEntity recordEntity :recordEntitylist){
			download downloadn = new download(t.getUrl());

			long currut = recordEntity.getStartTag()
					+ recordEntity.getCurrent();
			
			downloadn.setRequestMethod(HttpMethod.Get);
			// System.out.println("start1="+start1+"  ===== end1="+end1);
			downloadn.addHead(new Header("RANGE", "bytes=" + currut + "-"
					+ recordEntity.getEndTag()));
			downloadn.setRecordEntity(recordEntity);
			t.setRecordEntity(recordEntity);
			if(t.getTaskPriority()!=0){
 				downloadn.setTaskPriority(t.getTaskPriority());
			}else{
				downloadn.setTaskPriority(TaskPriority.LOWEST.getValue());
			}
			
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

	/**
	 * 
	 * ���߳����� ����װcallback
	 * 
	 * @param request
	 * @param callBack
	 * @return
	 */

	private <T> HttpCallBack<ResponseBody> getCallBack(
			final BaseHttpRequest<T> request,
			final HttpCallBack<ResponseBody<T>> callBack) {

		final String key = request.getUrl();

		if (listcallback == null) {
			listcallback = new HashMap<String, HttpCallBack<ResponseBody>>();
		}

		if (listcallback.containsKey(key)) {

			return listcallback.get(key);

		} else {

			final DownProgrossCallback downProgrossCallback = (DownProgrossCallback) callBack;
			final RecordEntity recordEntity = ((download) request)
					.getRecordEntity();
			DownProgrossCallback d = null;
			d = new DownProgrossCallback<ResponseBody>() {
				
				/**
				 * 1.�µ�����ʱ����Ϊ getTotalDownloaded Ĭ��Ϊ0
				 * 2.�ӱ��ؼ�¼�ļ��ϣ���ȡ�Ѿ������������ڸ��½����� 
				 */
				AtomicLong atomic = new AtomicLong(recordEntity.getTotalDownloaded());
				
				
				
				
				AtomicInteger taskNum = new AtomicInteger(0);

				@Override
				public void download_current(long current, long total) {
					// TODO Auto-generated method stub
					long current1 = atomic.addAndGet(current);
					downProgrossCallback.download_current(current1,
							recordEntity.getFilelength());
				}

				@Override
				public void success(ResponseBody result) {
					// TODO Auto-generated method stub
					super.success(result);
					RecordEntity re = result.getRecordEntity();
					taskNum.addAndGet(1);
					System.out.println("atomic.get()=" + atomic.get()
							+ " re.getFilelength()=" + re.getFilelength()
							+ " taskNum.get() = " + taskNum.get()
							+ "  re.getTotalNum()=" + re.getTotalNum());
					System.out.println(atomic.get() == re.getFilelength()
							&& taskNum.get() == re.getTotalNum());
				
					if (atomic.get() == re.getFilelength()
							&& taskNum.get() == re.getTotalNum()) {
						String refile = re.getRecordPath();
						System.out.println(new File(refile)
						.getParent());
						DeleteDirectory.deleteDir(new File(refile)
								.getParentFile());
						downProgrossCallback.success(result);
						listcallback.remove(key);
					}

				}

				@Override
				public void fail(Exception e, ResponseBody response) {
					// TODO Auto-generated method stub
					super.fail(e, response);
					callBack.fail(e, response);
					retryDownload(e, response.getRequestParam(), callBack);
				}

				@Override
				public void start() {
					// TODO Auto-generated method stub
					super.start();
					callBack.start();
				}

				@Override
				public void finish() {
					// TODO Auto-generated method stub
					super.finish();
					callBack.finish();
				}

			};
			listcallback.put(key, d);
			return d;
		}

	}

	/**
	 * ����ʧ�ܺ����ԣ���������װ����ŵ�����
	 * 
	 * @param e
	 * @param t
	 * @param httpCallback
	 */
	protected <T> void retryDownload(Exception e, final BaseHttpRequest<T> t,
			final HttpCallBack<ResponseBody<T>> httpCallback) {
		// TODO Auto-generated method stub
		if (e instanceof CancledOrInterruptedExcetion)
			return;

		if (t instanceof download) {
			RecordEntity recordEntity = ((download) t).getRecordEntity();
			((download) t).addHead(new Header("RANGE", "bytes="
					+ (recordEntity.getCurrent()+recordEntity.getStartTag()) + "-"
					+ recordEntity.getEndTag()));
			newRequest2(t, httpCallback);
		}

	}

	/**
	 * ���߳������ļ�
	 * 
	 * @param fileRequest
	 * @param httpCallback
	 * @return handler
	 */
	public TaskHandler download(final FileRequest fileRequest,
			final HttpCallBack<ResponseBody<File>> httpCallback) {

		long contentlength = 0;
		try {
			URL url = new URL(fileRequest.getUrl());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			contentlength = con.getContentLength();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String path = fileRequest.getFilepath();
		File file = new File(path);
		RecordEntity recordEntity = new RecordEntity();

		if (file.exists()) {
			if (file.length() == contentlength) {
				ResponseBody responseBody = fileRequest.getResponse();
				responseBody.setResult(new File(fileRequest.getFilepath()));
				httpCallback.current(file.length(), contentlength);
				httpCallback.success(responseBody);
				return null;
			}
			fileRequest.addHead(new Header("RANGE", "bytes=" + file.length()
					+ "-"));
			recordEntity.setStartTag(file.length());
		}

		recordEntity.setType(RecordType.DOWN);
		fileRequest.setRecordEntity(recordEntity);
		fileRequest.setRequestMethod(HttpMethod.Get);

		DownProgrossCallback<ResponseBody<File>> resCallback = new DownProgrossCallback<ResponseBody<File>>() {

			@Override
			public void download_current(long current, long total) {
				// TODO Auto-generated method stub
				((DownProgrossCallback<ResponseBody<File>>) httpCallback)
						.download_current(current, total);
			}

			@Override
			public void success(ResponseBody<File> result) {
				// TODO Auto-generated method stub
				super.success(result);
				httpCallback.success(result);
			}

			@Override
			public void fail(Exception e, ResponseBody<File> responseBody) {
				// TODO Auto-generated method stub
				super.fail(e, responseBody);
				httpCallback.fail(e, responseBody);

				if (fileRequest.isRetry())
					download(fileRequest, httpCallback);
			}

			@Override
			public void finish() {
				// TODO Auto-generated method stub
				super.finish();
				httpCallback.finish();
			}

			@Override
			public void start() {
				// TODO Auto-generated method stub
				super.start();
				httpCallback.start();
			}

		};

		return newRequest2(fileRequest, resCallback);
	}

	public static void removeRequest(TaskHandler task) {
		if (task instanceof BaseHttpTask) {

			SyncPoolExecutor.cancle(((BaseHttpTask) task).getTaskWorkIndex());

		}
	}

	/**
	 * ��ʼ��������Ϣ
	 * 
	 * @param request
	 */
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

	// ��ӡ��Ӧ��ͷ��Ϣ
	public void logResponseHead(URLConnection con) {

		for (int i = 1;; i++) {
			String header = con.getHeaderFieldKey(i);
			if (header != null)
				// responseHeaders.put(header,httpConnection.getHeaderField(header));
				LogUtils.e(header + " : " + con.getHeaderField(header));
			else
				break;
		}

	}

}
