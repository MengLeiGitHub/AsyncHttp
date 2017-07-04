package com.async.pool.msg;

public class Result {

	 private String id;
	 private Object obj;
	 
	 
	 public Result(String id,Object obj){
		 this.id=id;
		 this.obj=obj;
	 }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	 

}
