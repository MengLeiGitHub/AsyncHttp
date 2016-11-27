package com.async.http.requsetcreator.parseType.param.impl;

import java.lang.reflect.Parameter;

import com.async.http.annotation.param.Path;
import com.async.http.requsetcreator.parseType.param.ParamParseTypeInteface;

public class PathParse implements ParamParseTypeInteface{

	public String getType(Parameter field) {
		// TODO Auto-generated method stub
		Path path=field.getAnnotation(Path.class);
		return path.value();
	}

}
