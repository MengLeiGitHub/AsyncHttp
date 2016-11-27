package com.async.http.requsetcreator.dao.impl;

import java.io.File;
import java.util.List;

import com.async.http.clientImpl.HttpMethod;
import com.async.http.constant.Charsets;
import com.async.http.constant.Constents;
import com.async.http.exception.HttpException;
import com.async.http.request2.BaseHttpRequest;
import com.async.http.request2.UploadRequest;
import com.async.http.request2.part.FileParamPart;
import com.async.http.requsetcreator.dao.RequesyTypeInterface;
import com.async.http.requsetcreator.entity.CreatorBeans;
import com.async.http.requsetcreator.entity.MultiPart;
import com.async.http.requsetcreator.entity.ParamBean;

public class UploadImpl implements RequesyTypeInterface {

	
	CreatorBeans creatorBeans;
	
	public UploadImpl(CreatorBeans creatorBeans) {
		// TODO Auto-generated constructor stub
		this.creatorBeans=creatorBeans;
	}

	public BaseHttpRequest holdRequest() throws Exception {
		// TODO Auto-generated method stub

		String url = creatorBeans.getUrl();
		UploadRequest resReques = new UploadRequest(url, Charsets.UTF_8);
		List<ParamBean> params = creatorBeans.getList();
		if(params==null||params.size()==0){
			throw new HttpException("上传要有上传数据啊 ") {
			};
		}
		for(ParamBean p:params){
			if(p.getVal()!=null){
					if(p.getVal() instanceof String){
						resReques.addParam(new FileParamPart(p.getKey(), new File((String) p.getVal()),
								Constents.TYPE_IMAGE));
					}else if(p.getVal() instanceof File){
						resReques.addParam(new FileParamPart(p.getKey(), new File((String) p.getVal()),
								Constents.TYPE_IMAGE));
					}else if(p.getVal() instanceof MultiPart){
						MultiPart multiPart=(MultiPart) p.getVal() ;
						resReques.addParam(new FileParamPart(p.getKey(), new File(multiPart.getFilepath()),
								multiPart.getFiletype()));
					}
			}
			
		}
		resReques.setRequestMethod(HttpMethod.Post);
		return resReques;
	}

}
