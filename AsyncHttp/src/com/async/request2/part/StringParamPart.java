package com.async.request2.part;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.async.request2.BaseHttpRequest;

public class StringParamPart extends BaseParamPart<String> {
	
    String Enter = "\r\n";  

	
	public StringParamPart(String key, String val) {
		super(key, val);
		// TODO Auto-generated constructor stub
	}
	
	public StringParamPart(String string, String string2, String string3,
			String string4) {
		// TODO Auto-generated constructor stub
		super(  string,   string2,   string3,
				  string4);
	}

	@Override
	public long getContentLength() {
		// TODO Auto-generated method stub
		return getBytes().length;
	}

 

	@Override
	public void writeContent(OutputStream output,BaseHttpRequest<?> reqest)throws IOException {
		// TODO Auto-generated method stub
		 
   		output.write((val).getBytes(CHAR_DECODE));
 
	}

	@Override
	public byte[] getDisposition() throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder();
		sb.append("Content-Disposition:form-data;name=\""+key+"\";");


		
		return sb.toString().getBytes(CHAR_DECODE);
	}

	@Override
	public byte[] getContenType()throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder();
		sb.append("Content-Type:" +ParamType+";charset=");
		sb.append(CHAR_DECODE);
 		return sb.toString().getBytes(CHAR_DECODE);
		
	}

}
