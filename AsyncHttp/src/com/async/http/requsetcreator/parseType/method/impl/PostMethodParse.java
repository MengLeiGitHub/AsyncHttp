package com.async.http.requsetcreator.parseType.method.impl;

import java.lang.reflect.Method;

import com.async.http.annotation.POST;
import com.async.http.requsetcreator.parseType.method.MethodParseTypeInteface;

public class PostMethodParse implements MethodParseTypeInteface {

	public String getType(Method method) {
		// TODO Auto-generated method stub
		POST   post=	 method.getAnnotation(POST.class);
		return post.value();
	}

}
