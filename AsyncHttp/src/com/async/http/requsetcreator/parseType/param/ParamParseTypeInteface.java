package com.async.http.requsetcreator.parseType.param;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;


public interface ParamParseTypeInteface {
	
	public   String  getType(Parameter field);

}
