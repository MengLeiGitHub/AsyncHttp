package com.async.http.proxy.parseType.method.impl;

import com.async.http.annotation.JSONPOST;
import com.async.http.proxy.parseType.method.MethodParseTypeInteface;

import java.lang.reflect.Method;

public class JSONPostMethodParse implements MethodParseTypeInteface {

	public String getType(Method method) {
		// TODO Auto-generated method stub
		JSONPOST post=	 method.getAnnotation(JSONPOST.class);
		return post.value();
	}

}
