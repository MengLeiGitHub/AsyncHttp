package com.async.http.requsetcreator.parseType.method.impl;

import java.lang.reflect.Method;

import com.async.http.annotation.PATCH;
import com.async.http.requsetcreator.parseType.method.MethodParseTypeInteface;

public class PatchMethodParse implements MethodParseTypeInteface {

	public String getType(Method method) {
		// TODO Auto-generated method stub
		PATCH   patch=	 method.getAnnotation(PATCH.class);
		return  patch.value();
	}

}
