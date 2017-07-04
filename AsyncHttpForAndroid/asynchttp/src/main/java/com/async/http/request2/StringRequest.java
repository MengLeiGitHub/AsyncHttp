package com.async.http.request2;

import com.async.http.request2.convert.BaseDataConvert;
import com.async.http.request2.convert.StringDataConvert;
import com.async.http.request2.writer.BaseWriter;
import com.async.http.request2.writer.OneByOneWriter;

public class StringRequest extends BaseHttpRequest<String> {

	public StringRequest(String url){
		super(url);

	}

 	public StringRequest(String url,String charCncode){
		super(url);
		setDataConverCharset(charCncode);
	}

	@Override
	public BaseDataConvert<String> getConvert() {
		// TODO Auto-generated method stub
		return   new  StringDataConvert();
	}


	@Override
	public BaseWriter getWriter() {
		return new OneByOneWriter(this);
	}
 	
	
}
