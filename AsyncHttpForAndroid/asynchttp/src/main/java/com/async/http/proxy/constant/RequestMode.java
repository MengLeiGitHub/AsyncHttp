package com.async.http.proxy.constant;

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
import com.async.http.annotation.param.PathParam;
import com.async.http.client.HttpMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestMode {
	private static RequestMode requestMode;
	private Map<String, HttpMethod> methods;
	private static List<Class> methodsClass = new ArrayList<Class>();
	private static List<Class> paramClass = new ArrayList<Class>();




	protected RequestMode() {

		if (methods == null)
			methods = new HashMap<String, HttpMethod>();

		//表示方法的 注解和网络请求的具体实现
		methods.put(POST.class.getName(), HttpMethod.Post);
		methods.put(GET.class.getName(), HttpMethod.Get);
		methods.put(JSONPOST.class.getName(), HttpMethod.Post);
		methods.put(DOWNLOAD.class.getName(), HttpMethod.Get);
		methods.put(UPLOAD.class.getName(), HttpMethod.Post);
		methods.put(PUT.class.getName(), HttpMethod.Post);
		methods.put(DELETE.class.getName(), HttpMethod.Post);
		methods.put(TRACE.class.getName(), HttpMethod.Post);
		methods.put(OPTIONS.class.getName(), HttpMethod.Post);
		methods.put(HEAD.class.getName(), HttpMethod.Post);

		methods.put(PATCH.class.getName(), HttpMethod.Post);





		//表示方法的 注解结合
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










		//表示参数 注解
		paramClass.add(PathParam.class);
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
