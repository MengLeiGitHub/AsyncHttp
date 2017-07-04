package com.async.http.proxy.parseType.param.impl;

import com.async.http.annotation.param.PathParam;
import com.async.http.proxy.parseType.param.ParamParseTypeInteface;

import java.lang.annotation.Annotation;

public class PathParse implements ParamParseTypeInteface{

	public String getType(Annotation field) {
		// TODO Auto-generated method stub

		return ((PathParam)field).value();
	}

}
