package com.async.http.proxy.parseType.method.impl;

import java.lang.reflect.Method;

import com.async.http.annotation.UPLOAD;
import com.async.http.proxy.parseType.method.MethodParseTypeInteface;

public class UploadMethodParse implements MethodParseTypeInteface {

	public String getType(Method method) {
		// TODO Auto-generated method stub
		UPLOAD   get=	 method.getAnnotation(UPLOAD.class);
		return get.value();
	}

}
