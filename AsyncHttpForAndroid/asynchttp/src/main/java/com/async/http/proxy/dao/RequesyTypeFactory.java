package com.async.http.proxy.dao;

import com.async.http.annotation.DOWNLOAD;
import com.async.http.proxy.dao.impl.CustomRequestImpl;
import com.async.http.proxy.dao.impl.MultitierDownloadImpl;
import com.async.http.proxy.dao.impl.JsonImpl;
import com.async.http.proxy.dao.impl.SingleThreadImpl;
import com.async.http.proxy.dao.impl.StringImpl;
import com.async.http.proxy.dao.impl.UploadImpl;
import com.async.http.proxy.entity.CreatorBeans;


public class RequesyTypeFactory {
	
	 
	public RequesyTypeInterface  getRequestType( CreatorBeans creatorBeans){

		if(creatorBeans.isDownload()){
			if(creatorBeans.MultitierDownload()){
				return new MultitierDownloadImpl(creatorBeans);
			}else {
				return new SingleThreadImpl(creatorBeans);
			}
		}else  if(creatorBeans.isUpload()){
			return new UploadImpl(creatorBeans);

		}else if(creatorBeans.isJSONPOST()) {
			return new JsonImpl(creatorBeans);
		}else if(creatorBeans.getRequestImpl()!=null){
			return new CustomRequestImpl(creatorBeans);
		}



		return new StringImpl(creatorBeans);
	}
	

}
