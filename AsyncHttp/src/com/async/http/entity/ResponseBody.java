package com.async.http.entity;

import com.async.http.exception.HttpException;
import com.async.http.request2.BaseHttpRequest;
import com.async.http.request2.record.RecordEntity;

public class ResponseBody<T> {

	private HttpException exception;
	private T result;
	private BaseHttpRequest<T> requestParam;
	private long contentLength;
	private long requestParamLength;
	private int index;

	private RecordEntity recordEntity;
	
	
	public T getResult() {
		return result;
	}

	public ResponseBody setResult(T result) {
		this.result = result;
		return this;
	}

	public BaseHttpRequest<T> getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(BaseHttpRequest<T> requestParam) {
		this.requestParam = requestParam;
	}

	public HttpException getException() {
		return exception;
	}

	public void setException(HttpException exception) {
		this.exception = exception;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	public long getRequestParamLength() {
		return requestParamLength;
	}

	public void setRequestParamLength(long requestParamLength) {
		this.requestParamLength = requestParamLength;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public RecordEntity getRecordEntity() {
		return recordEntity;
	}

	public void setRecordEntity(RecordEntity recordEntity) {
		this.recordEntity = recordEntity;
	}

}
