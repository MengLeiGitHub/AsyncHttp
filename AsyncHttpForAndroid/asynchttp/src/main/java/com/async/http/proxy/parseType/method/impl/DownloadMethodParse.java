package com.async.http.proxy.parseType.method.impl;

import java.lang.reflect.Method;

import com.async.http.annotation.DOWNLOAD;
import com.async.http.proxy.parseType.method.MethodParseTypeInteface;

public class DownloadMethodParse implements MethodParseTypeInteface {

	public String getType(Method method) {
		// TODO Auto-generated method stub
		DOWNLOAD   download=	 method.getAnnotation(DOWNLOAD.class);

		return download.value();
	}

}
