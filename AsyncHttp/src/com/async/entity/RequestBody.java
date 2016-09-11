package com.async.entity;

import java.util.Hashtable;
import java.util.Set;

public class RequestBody {

 	private  Hashtable table;
	
	public RequestBody(){
 		if(table==null)table=new Hashtable<String, Object>();
 		
	}
	
	
	
	public final void  addParam(String key,String param){
		table.put(key, param);
		
	}
	
	
	public final  Object queryParamByKey(String key){
		return table.get(key);
	}
	
	public  final Set  getParamKeys(){
		return table.entrySet();
	}
	
}
