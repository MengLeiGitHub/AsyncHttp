package com.async.http.requsetcreator.parseType.param.impl;

import java.lang.reflect.Parameter;

import com.async.http.annotation.param.Param;
import com.async.http.annotation.param.Path;
import com.async.http.requsetcreator.parseType.param.ParamParseTypeInteface;

public class ParamParse implements ParamParseTypeInteface{

	public String getType(Parameter field) {
		// TODO Auto-generated method stub
		Param path=field.getAnnotation(Param.class);
		return path.value();
	}

}
