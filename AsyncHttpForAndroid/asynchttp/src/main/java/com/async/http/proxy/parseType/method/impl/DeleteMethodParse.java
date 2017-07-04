package com.async.http.proxy.parseType.method.impl;

import java.lang.reflect.Method;

import com.async.http.annotation.DELETE;
import com.async.http.proxy.parseType.method.MethodParseTypeInteface;

public class DeleteMethodParse implements MethodParseTypeInteface {

	public String getType(Method method) {
		// TODO Auto-generated method stub
		DELETE   get=	 method.getAnnotation(DELETE.class);
		return get.value();
	}

}
