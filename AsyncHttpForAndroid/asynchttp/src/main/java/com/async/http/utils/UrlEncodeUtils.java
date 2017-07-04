package com.async.http.utils;

import java.util.HashMap;
import java.util.Iterator;

public class UrlEncodeUtils {

	 public static  String   encodeUrl(String url){
		 
		 HashMap<String, String> hashMap=new HashMap<String, String>();
		 hashMap.put(" ", "%20");
		 hashMap.put("-", "%22");
		 hashMap.put("#", "%23");
		 hashMap.put("\\(", "%28");
		 hashMap.put("\\)", "%29");
		 hashMap.put("\\+", "%2B");
		 hashMap.put(",", "%2C");
		 hashMap.put(";", "%3B");
		 hashMap.put("<", "%3C");
		 hashMap.put(">", "%3E");
		 hashMap.put("@", "%40");
 		 hashMap.put("\\|", "%7C");
		 hashMap.put("\\\\", "%5C");
 		 Iterator<String> iterator=hashMap.keySet().iterator();
 		  while(iterator.hasNext()){
  			  String key=iterator.next();
 			  String val=hashMap.get(key);
  			 url=url.replaceAll(key, val);
  		  }
 		 
 		 return url;
 	 }

}
