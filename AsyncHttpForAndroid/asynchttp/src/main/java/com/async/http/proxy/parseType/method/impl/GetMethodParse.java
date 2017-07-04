package com.async.http.proxy.parseType.method.impl;

import java.lang.reflect.Method;

import com.async.http.annotation.GET;
import com.async.http.proxy.parseType.method.MethodParseTypeInteface;

public class GetMethodParse implements MethodParseTypeInteface {

	public String getType(Method method) {
		// TODO Auto-generated method stub
		GET   get=	 method.getAnnotation(GET.class);
		return get.value();
	}

}
