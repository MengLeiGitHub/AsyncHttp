package com.async.builder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.async.constant.RequestType;
import com.async.entity.RequestBody;
import com.async.entity.URLConfig;






public class RequestBuilder {

	private  RequestBody  requestBody;
	private  URLConfig    urlConfig;

	public   RequestBuilder  build(){
		
		return this;
	}

	public   RequestBuilder  config(URLConfig config){
		this.urlConfig=config;
		
		 
		return this;
	}
	
	public   String  getUrl(){
		return urlConfig.getUrl();
	}
	public   HashMap   getParams(){
		
		return  urlConfig.getMap();
	}
	
	
	 
	public URLConfig getUrlConfig() {
		return urlConfig;
	}

	public   String   getConenctionParams(){
		return  getParam(urlConfig.getMap(),urlConfig.getRequestType());
	}
	
	
	
	private String getParam(HashMap<String, Object> map,int type) {
		try {
			if (map == null) {
				throw new Exception("URLConfig should not be null");
			}
			StringBuffer sb = new StringBuffer();
			Set set = map.entrySet();
			Iterator iterator = set.iterator();
			int index = 0;
			while (iterator.hasNext()) {
				Map.Entry<String, Object> me = (Entry<String, Object>) iterator
						.next();
				Object obj = me.getValue();
				if (obj instanceof String) {
					if (index == 0){
						if(type==RequestType.GET)
						   sb.append("?" + me.getKey() + "=" + obj.toString());
						else
						   sb.append( me.getKey() + "=" + obj.toString());
 					}
					
					else
						sb.append("&" + me.getKey() + "=" + obj.toString());

				}
				index++;
			}

			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
}
