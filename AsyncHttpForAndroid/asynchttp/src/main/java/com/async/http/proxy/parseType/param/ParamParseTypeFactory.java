package com.async.http.proxy.parseType.param;

import java.util.HashMap;

import com.async.http.annotation.param.Param;
import com.async.http.annotation.param.PathParam;
import com.async.http.proxy.parseType.param.impl.ParamParse;
import com.async.http.proxy.parseType.param.impl.PathParse;


public class ParamParseTypeFactory {
	private static HashMap<String, ParamParseTypeInteface>  parserMap=new HashMap<String, ParamParseTypeInteface>();

	static {
		PathParse stringParser=new PathParse();
		parserMap.put(PathParam.class.getName(),stringParser );
		
		
		ParamParse  paramParser=new ParamParse();
		parserMap.put(Param.class.getName(),paramParser );

		
	}

	public static  ParamParseTypeInteface  getParseType(Class type){
		return parserMap.get(type.getName());
	}
	
	
	
}
