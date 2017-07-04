package com.async.http.request2;

import com.async.http.callback.HttpCallBack;
import com.async.http.entity.ResponseBody;
import com.async.http.request2.convert.BaseDataConvert;
import com.async.http.request2.convert.FileDataConvert;
import com.async.http.request2.record.RecordEntity;
import com.async.http.request2.writer.BaseWriter;
import com.async.http.request2.writer.OneByOneWriter;

import java.io.File;

public class FileRequest extends BaseHttpRequest<File> {
	
	private String  filepath;
	private  String url;
	private  RecordEntity recordEntity;
	
	public FileRequest(HttpCallBack<ResponseBody<File>> httpLifeCycleInterface) {
		super(httpLifeCycleInterface);
		// TODO Auto-generated constructor stub
		
	}
	public FileRequest(String url){
		super(url);
	}

	public FileRequest(HttpCallBack<ResponseBody<File>> httpLifeCycleInterface,String url, String filepath2) {
		// TODO Auto-generated constructor stub
		super(httpLifeCycleInterface);
		setUrl(url);
		filepath=filepath2;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getFilepath() {
		return filepath;
	}
	
	
	
	
	@Override
	public BaseDataConvert<File> getConvert() {
		// TODO Auto-generated method stub
		return new FileDataConvert();//数据解析
	}
	public RecordEntity getRecordEntity() {
		return recordEntity;
	}
	public void setRecordEntity(RecordEntity recordEntity) {
		this.recordEntity = recordEntity;
	}

	@Override
	public BaseWriter getWriter() {
		return new OneByOneWriter(this);
	}
}
