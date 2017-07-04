package com.async.http.proxy.parseType.method.impl;

import java.lang.reflect.Method;

import com.async.http.annotation.PUT;
import com.async.http.proxy.parseType.method.MethodParseTypeInteface;

public class PutMethodParse implements MethodParseTypeInteface {

	public String getType(Method method) {
		// TODO Auto-generated method stub
		PUT   put=	 method.getAnnotation(PUT.class);
		return put.value();
	}

}
