package com.async.http.request2;

import com.async.http.callback.HttpCallBack;
import com.async.http.entity.ResponseBody;
import com.async.http.request2.convert.BaseDataConvert;
import com.async.http.request2.convert.RandomAccessFileDataConvert;
import com.async.http.request2.record.RecordEntity;
import com.async.http.request2.writer.BaseWriter;
import com.async.http.request2.writer.OneByOneWriter;

import java.io.File;

public class download extends BaseHttpRequest<File> {
	 
	private RecordEntity recordEntity;
	String  filepath;
	
	public download(RecordEntity recordEntity) {
		super(recordEntity.getUrl());
		// TODO Auto-generated constructor stub
		this.recordEntity=recordEntity;
		filepath=recordEntity.getFilePath();
	}

	
	public String getFilepath() {
		return filepath;
	}


	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}


	public download(
			HttpCallBack<ResponseBody<File>> httpLifeCycleInterface) {
		super(httpLifeCycleInterface);
		// TODO Auto-generated constructor stub
	}

	public download(String url){
		super(url);
	}
	
	
	public download(RecordEntity recordEntity,
			HttpCallBack<ResponseBody<File>> httpLifeCycleInterface) {
		super(httpLifeCycleInterface);
		this.recordEntity=recordEntity;
 	}
	 

	@Override
	public BaseDataConvert<File> getConvert() {
		// TODO Auto-generated method stub
		return new RandomAccessFileDataConvert();
	}

	@Override
	public BaseWriter getWriter() {
		return new OneByOneWriter(this);
	}

	public RecordEntity getRecordEntity() {
		return recordEntity;
	}

	public void setRecordEntity(RecordEntity recordEntity) {
		this.recordEntity = recordEntity;
	}

	 
}
