package com.async.http.requsetcreator.parseType.method.impl;

import java.lang.reflect.Method;

import com.async.http.annotation.OPTIONS;
import com.async.http.requsetcreator.parseType.method.MethodParseTypeInteface;

public class OptionsMethodParse implements MethodParseTypeInteface {

	public String getType(Method method) {
		// TODO Auto-generated method stub
		OPTIONS   options=	 method.getAnnotation(OPTIONS.class);
		return    options.value();
	}

}
