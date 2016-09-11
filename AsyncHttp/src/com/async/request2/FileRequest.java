package com.async.request2;

import java.io.File;

import com.async.callback.HttpCallBack;
import com.async.entity.ResponseBody;
import com.async.request2.convert.BaseDataConvert;
import com.async.request2.convert.FileDataConvert;

public class FileRequest extends BaseHttpRequest<File> {
	
	private String  filepath;
	private  String url;

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
		return new FileDataConvert();
	}

	 
}
