package com.async.http.proxy.parseType.param.impl;

import com.async.http.annotation.param.Param;
import com.async.http.proxy.parseType.param.ParamParseTypeInteface;

import java.lang.annotation.Annotation;

public class ParamParse implements ParamParseTypeInteface{

	public String getType(Annotation field) {
		// TODO Auto-generated method stub
		return ((Param)field).value();
	}

}
