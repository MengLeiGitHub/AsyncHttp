package com.async.http.requsetcreator.parseType.method.impl;

import java.lang.reflect.Method;

import com.async.http.annotation.DELETE;
import com.async.http.annotation.GET;
import com.async.http.annotation.HEAD;
import com.async.http.annotation.POST;
import com.async.http.requsetcreator.parseType.method.MethodParseTypeInteface;

public class HeadMethodParse implements MethodParseTypeInteface {

	public String getType(Method method) {
		// TODO Auto-generated method stub
		HEAD   head=	 method.getAnnotation(HEAD.class);
		return head.value();
	}

}
