package com.async.pool.Log;

public class Log {

	static  boolean  isWrite=true;
	public static void  e(String msg){
		if(isWrite)
		System.err.println(msg);;
	}
	public static  void  setDebug(boolean is){
		isWrite=is;
	}
}
