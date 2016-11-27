package com.async.http.requsetcreator.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.shape.Arc;

import com.async.http.annotation.DELETE;
import com.async.http.annotation.DOWNLOAD;
import com.async.http.annotation.GET;
import com.async.http.annotation.HEAD;
import com.async.http.annotation.JSONPOST;
import com.async.http.annotation.OPTIONS;
import com.async.http.annotation.PATCH;
import com.async.http.annotation.POST;
import com.async.http.annotation.PUT;
import com.async.http.annotation.TRACE;
import com.async.http.annotation.UPLOAD;
import com.async.http.annotation.param.Param;
import com.async.http.annotation.param.Path;
import com.async.http.clientImpl.HttpMethod;
import com.async.http.requsetcreator.Creator;
import com.async.http.requsetcreator.dao.impl.StringImpl;
import com.async.http.requsetcreator.dao.impl.UploadImpl;

public class RequestMode {
	private static RequestMode requestMode;
	private Map<String, HttpMethod> methods;
	private static List<Class> methodsClass = new ArrayList<Class>();
	private static List<Class> paramClass = new ArrayList<Class>();
	
	
	
	
	protected RequestMode() {
	
		if (methods == null)
			methods = new HashMap<String, HttpMethod>();

		//��ʾ������ ע�����������ľ���ʵ��
		methods.put(POST.class.getName(), HttpMethod.Post);
		methods.put(GET.class.getName(), HttpMethod.Get);
		methods.put(JSONPOST.class.getName(), HttpMethod.Post);

		
		
		
		
		

		//��ʾ������ ע����
		methodsClass.add(POST.class);
		methodsClass.add(GET.class);
		methodsClass.add(DELETE.class);
		methodsClass.add(HEAD.class);
		methodsClass.add(OPTIONS.class);
		methodsClass.add(PATCH.class);
		methodsClass.add(PUT.class);
		methodsClass.add(TRACE.class);
		methodsClass.add(UPLOAD.class);
		methodsClass.add(DOWNLOAD.class);
		methodsClass.add(JSONPOST.class);

		
		
		
		
		
		
		
		
		
		//��ʾ���� ע��
		paramClass.add(Path.class);
		paramClass.add(Param.class);


	}

	public static RequestMode INSTANCE() {
		if (requestMode == null)
			requestMode = new RequestMode();

		return requestMode;
	}

 
	public Map<String, HttpMethod> getMethods() {

		return methods;
	}
	
	public List<Class>  getClasss(){
		return methodsClass;
	}
	
	public  List<Class>   getParamClass(){
		return paramClass;

	}
	
	
}
