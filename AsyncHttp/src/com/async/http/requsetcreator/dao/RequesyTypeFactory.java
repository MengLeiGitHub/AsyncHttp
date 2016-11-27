package com.async.http.requsetcreator.dao;

import com.async.http.requsetcreator.dao.impl.DownloadImpl;
import com.async.http.requsetcreator.dao.impl.JsonImpl;
import com.async.http.requsetcreator.dao.impl.UploadImpl;
import com.async.http.requsetcreator.entity.CreatorBeans;




public class RequesyTypeFactory {
	
	 
	public RequesyTypeInterface  getRequestType( CreatorBeans creatorBeans){
		
		if(creatorBeans.isDownload()){
			
			return new DownloadImpl(creatorBeans);
			
		}else  if(creatorBeans.isUpload()){
			return new UploadImpl(creatorBeans);

		}else if(creatorBeans.isJSONPOST()) {
			return new JsonImpl(creatorBeans);

		}
		
		return null;
		
	}
	

}
