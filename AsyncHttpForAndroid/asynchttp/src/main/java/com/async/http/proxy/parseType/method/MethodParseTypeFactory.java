package com.async.http.proxy.parseType.method;

import com.async.http.annotation.DELETE;
import com.async.http.annotation.DOWNLOAD;
import com.async.http.annotation.GET;
import com.async.http.annotation.HEAD;
import com.async.http.annotation.JSONPOST;
import com.async.http.annotation.OPTIONS;
import com.async.http.annotation.PATCH;
import com.async.http.annotation.POST;
import com.async.http.annotation.PUT;
import com.async.http.annotation.TRACE;
import com.async.http.annotation.UPLOAD;
import com.async.http.proxy.parseType.method.impl.DeleteMethodParse;
import com.async.http.proxy.parseType.method.impl.DownloadMethodParse;
import com.async.http.proxy.parseType.method.impl.GetMethodParse;
import com.async.http.proxy.parseType.method.impl.HeadMethodParse;
import com.async.http.proxy.parseType.method.impl.JSONPostMethodParse;
import com.async.http.proxy.parseType.method.impl.OptionsMethodParse;
import com.async.http.proxy.parseType.method.impl.PatchMethodParse;
import com.async.http.proxy.parseType.method.impl.PostMethodParse;
import com.async.http.proxy.parseType.method.impl.PutMethodParse;
import com.async.http.proxy.parseType.method.impl.TraceMethodParse;
import com.async.http.proxy.parseType.method.impl.UploadMethodParse;

import java.util.HashMap;


public class MethodParseTypeFactory {
	private static HashMap<String, MethodParseTypeInteface>  parserMap=new HashMap<String, MethodParseTypeInteface>();

	static {
		GetMethodParse stringParser=new GetMethodParse();
		parserMap.put(GET.class.getName(),stringParser );
		 
		PostMethodParse postmethod=new PostMethodParse();
		parserMap.put(POST.class.getName(),postmethod );

		JSONPostMethodParse jsonPostMethodParse=new JSONPostMethodParse();
		parserMap.put(JSONPOST.class.getName(),jsonPostMethodParse );


		DeleteMethodParse deleteMethodParse=new DeleteMethodParse();
		parserMap.put(DELETE.class.getName(),deleteMethodParse );
		
		
		DownloadMethodParse downloadMethodParse=new DownloadMethodParse();
		parserMap.put(DOWNLOAD.class.getName(),downloadMethodParse );
		
		
		UploadMethodParse  uploadMethodParse=new UploadMethodParse();
		parserMap.put(UPLOAD.class.getName(),uploadMethodParse );

		
		OptionsMethodParse optionsMethodParse=new OptionsMethodParse();
		parserMap.put(OPTIONS.class.getName(),optionsMethodParse );

		
		PutMethodParse  putMethodParse=new PutMethodParse();
		
		parserMap.put(PUT.class.getName(),putMethodParse );
		
		
		HeadMethodParse headMethodParse=new HeadMethodParse();
		
		parserMap.put(HEAD.class.getName(),headMethodParse );

		
		TraceMethodParse  traceMethodParse=new TraceMethodParse();
		parserMap.put(TRACE.class.getName(),traceMethodParse );

		
		PatchMethodParse  patchMethodParse=new PatchMethodParse();
		parserMap.put(PATCH.class.getName(),patchMethodParse );

	}

	public static  MethodParseTypeInteface  getParseType(Class type){
		return parserMap.get(type.getName());
	}
	
	
	
}
