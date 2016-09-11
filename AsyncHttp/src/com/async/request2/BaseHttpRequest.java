package com.async.request2;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.async.Interceptor2.RequestInterceptor2;
import com.async.callback.HttpCallBack;
import com.async.clientImpl.HttpMethod;
import com.async.entity.ResponseBody;
import com.async.request2.convert.BaseDataConvert;
import com.async.request2.entity.Header;
import com.async.request2.part.BaseParamPart;
import com.async.request2.part.FileParamPart;

/**
 * request���أ��� response ���� request ����
 * 
 * @author ML
 * @param <S>
 * 
 */

public abstract class BaseHttpRequest<T> {

	
	private  String  url;
	
	private ArrayList<BaseParamPart> paramParts;

	private BaseDataConvert<T>  convert;

	private HttpMethod  RequestMethod;
	
	private  boolean  isStop=false;
	
	private  String   dataConverCharset;
	
	private  int  index;
	
	private  boolean  isUseCaChe;
	
	
	 /**
     * connect timeout
     */
    private int connectTimeout = 0;
    /**
     * socket timeout
     */
    private int socketTimeout = 0;
	
	
	
	
	
 	/**
	 * �ص���������
	 */
	private HttpCallBack<ResponseBody<T>> httpLifeCycleInterface;

	/**
	 * ������������
	 * 
	 */
	private RequestInterceptor2 requestInterceptor;

	/**
	 * 
	 * ���ӹ���
	 */

	ManagerConnectionInterface managerConnectionInterface;

	private ArrayList<Header> header;

	public BaseHttpRequest(String url) {
		if (header == null) {
			header = new ArrayList<Header>();
		}
		if (paramParts == null) {
			paramParts = new ArrayList<BaseParamPart>();
		}
		this.url=url;
	}
	public ArrayList<BaseParamPart> getParamParts() {
		return paramParts;
	}

	
	public <S extends BaseHttpRequest<T>> S addHeads(ArrayList<Header> header) {

		this.header.addAll(header);
 		return (S) this;
	}

	
	public <S extends BaseHttpRequest<T>> S addHead(Header header) {

		this.header.add(header);

		return (S) this;
	}

	public <S extends BaseHttpRequest<T>> S addHead(String key, String val) {

		this.header.add(new Header(key, val));

		return (S) this;
	}

	public <S extends BaseHttpRequest<T>> S addParam(
			BaseParamPart baseParamPart) {
		this.paramParts.add(baseParamPart);
		return (S) this;
	}

	
	public  abstract BaseDataConvert<T> getConvert();
	
	 
	public  <S extends BaseHttpRequest<T>> S setConvert(BaseDataConvert<T> convert) {
		this.convert = convert;
		return (S)this;
	}
	 

	public void addRequestInterceptor(RequestInterceptor2 requestInterceptor) {
		this.requestInterceptor = requestInterceptor;
	}

	public BaseHttpRequest(HttpCallBack<ResponseBody<T>> httpLifeCycleInterface) {
		this.httpLifeCycleInterface = httpLifeCycleInterface;
	}

	public void stop() {
		isStop=true;
		httpLifeCycleInterface.finish();
	}

	public void cancle() {
		managerConnectionInterface.cancle();
	}

	  
	 
	public ResponseBody<T> getResponse( ) {
		// TODO Auto-generated method stub
 		ResponseBody<T> responseBody = new ResponseBody<T>();
 		responseBody.setRequestParam(this);
 		responseBody.setIndex(index);
 		return responseBody;
	}
	
	
	public ArrayList<Header> getHeaders() {
		// TODO Auto-generated method stub
		return this.header;
	}
	public long getTotalParamLength() {
		// TODO Auto-generated method stub
		long len=0;
		for(BaseParamPart<T> part:paramParts){
			if(part instanceof FileParamPart)
			len+=part.getContentLength();
 		}
		return len;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public boolean   isCancledOrInterrupted(){
		return  isStop;
	}
	 
	public HttpCallBack<ResponseBody<T>> getCallBack(){
		return httpLifeCycleInterface;
	}
	public String getDataConverCharset() {
		return dataConverCharset;
	}
	public void setDataConverCharset(String dataConverCharset) {
		this.dataConverCharset = dataConverCharset;
	}
	public boolean isHaveSSL() {
		// TODO Auto-generated method stub
		return false;
	}
	public void registerCallBack(HttpCallBack<ResponseBody<T>> httpCallback) {
		// TODO Auto-generated method stub
		httpLifeCycleInterface=httpCallback;
	}
	public HttpMethod getRequestMethod() {
		return RequestMethod;
	}
	public void setRequestMethod(HttpMethod requestMethod) {
		RequestMethod = requestMethod;
	}
	public RequestInterceptor2 getRequestInterceptor() {
		return requestInterceptor;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public boolean isUseCaChe() {
		return isUseCaChe;
	}
	public void setUseCaChe(boolean isUseCaChe) {
		this.isUseCaChe = isUseCaChe;
	}
	
	
	public void setDefault(){
		
	}
	public int getConnectTimeout() {
		return connectTimeout;
	}
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	public int getSocketTimeout() {
		return socketTimeout;
	}
	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}
	
	
	
}