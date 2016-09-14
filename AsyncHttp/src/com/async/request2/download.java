package com.async.request2;

import java.io.RandomAccessFile;

import com.async.callback.HttpCallBack;
import com.async.entity.ResponseBody;
import com.async.request2.convert.BaseDataConvert;
import com.async.request2.convert.RandomAccessFileDataConvert;
import com.async.request2.record.RecordEntity;

public class download extends BaseHttpRequest<RandomAccessFile> {
	 
	private RecordEntity recordEntity;
	String  filepath;
	
	public download(RecordEntity recordEntity) {
		super(recordEntity.getUrl());
		// TODO Auto-generated constructor stub
		this.recordEntity=recordEntity;
		filepath=recordEntity.getFilePath();
	}

	
	public download(
			HttpCallBack<ResponseBody<RandomAccessFile>> httpLifeCycleInterface) {
		super(httpLifeCycleInterface);
		// TODO Auto-generated constructor stub
	}

	public download(String url){
		super(url);
	}
	
	
	public download(RecordEntity recordEntity,
			HttpCallBack<ResponseBody<RandomAccessFile>> httpLifeCycleInterface) {
		super(httpLifeCycleInterface);
		this.recordEntity=recordEntity;
 	}
	 

	@Override
	public BaseDataConvert<RandomAccessFile> getConvert() {
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
