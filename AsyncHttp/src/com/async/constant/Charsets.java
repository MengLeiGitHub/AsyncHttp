package com.async.constant;

import java.nio.charset.Charset;
 
public class Charsets {
	public static final String ASCII = "ASCII";
	public static final String US_ASCII = "US-ASCII";

	public static final String ISO_8859_1 = "ISO-8859-1";

	public static final String Unicode = "Unicode";

	public static final String BIG5 = "BIG5";

	public static final String UTF_8 = "UTF-8";
	public static final String UTF_16 = "UTF-16";

	public static final String GB2312 = "GB2312";
	public static final String GBK = "GBK";
	public static final String GB18030 = "GB18030";

	
	public static final  String POST="POST";
	public static final  String GET="GET";
	public static final  String DELETE="delete";
	public static final  String PUT="PUT";
	
	
	
	public static Charset getCharset(String charsetName){
		return Charset.forName(charsetName);
	}
}
