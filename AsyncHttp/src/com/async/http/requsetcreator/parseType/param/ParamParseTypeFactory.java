package com.async.http.requsetcreator.parseType.param;

import java.util.HashMap;

import com.async.http.annotation.GET;
import com.async.http.annotation.param.Param;
import com.async.http.annotation.param.Path;
import com.async.http.requsetcreator.parseType.method.impl.GetMethodParse;
import com.async.http.requsetcreator.parseType.param.impl.ParamParse;
import com.async.http.requsetcreator.parseType.param.impl.PathParse;


public class ParamParseTypeFactory {
	private static HashMap<String, ParamParseTypeInteface>  parserMap=new HashMap<String, ParamParseTypeInteface>();

	static {
		PathParse stringParser=new PathParse();
		parserMap.put(Path.class.getName(),stringParser );
		
		
		ParamParse  paramParser=new ParamParse();
		parserMap.put(Param.class.getName(),paramParser );

		
	}

	public static  ParamParseTypeInteface  getParseType(Class type){
		return parserMap.get(type.getName());
	}
	
	
	
}
