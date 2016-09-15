package com.async.http.request2;

import java.io.File;
import java.io.RandomAccessFile;

import com.async.http.callback.HttpCallBack;
import com.async.http.entity.ResponseBody;
import com.async.http.request2.convert.BaseDataConvert;
import com.async.http.request2.convert.RandomAccessFileDataConvert;
import com.async.http.request2.record.RecordEntity;

public class download extends BaseHttpRequest<File> {
	 
	private RecordEntity recordEntity;
	String  filepath;
	
	public download(RecordEntity recordEntity) {
		super(recordEntity.getUrl());
		// TODO Auto-generated constructor stub
		this.recordEntity=recordEntity;
		filepath=recordEntity.getFilePath();
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

	public RecordEntity getRecordEntity() {
		return recordEntity;
	}

	public void setRecordEntity(RecordEntity recordEntity) {
		this.recordEntity = recordEntity;
	}

	 
}
