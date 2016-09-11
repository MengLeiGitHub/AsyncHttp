package com.async.request2;

import com.async.request2.convert.BaseDataConvert;
import com.async.request2.convert.StringDataConvert;

public class StringRequest extends BaseHttpRequest<String> {
 	
 	public StringRequest(String url,String charCncode){
		super(url);
		setDataConverCharset(charCncode);
	}

	@Override
	public BaseDataConvert<String> getConvert() {
		// TODO Auto-generated method stub
		return   new  StringDataConvert();
	}
	
 	
 	
 	
	
}
