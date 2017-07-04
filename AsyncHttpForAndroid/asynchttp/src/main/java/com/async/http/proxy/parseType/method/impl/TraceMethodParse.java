package com.async.http.proxy.parseType.method.impl;

import java.lang.reflect.Method;

import com.async.http.annotation.TRACE;
import com.async.http.proxy.parseType.method.MethodParseTypeInteface;

public class TraceMethodParse implements MethodParseTypeInteface {

	public String getType(Method method) {
		// TODO Auto-generated method stub
		TRACE   trace=	 method.getAnnotation(TRACE.class);
		return trace.value();
	}

}
