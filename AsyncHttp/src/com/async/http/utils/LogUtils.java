package com.async.http.utils;

public class LogUtils {

	 private  static boolean   isOpenDebug=true;
	 
	 public static boolean    isDebug(){
		 
		 return isOpenDebug;
	 }
	 
	 public static void setDebug(boolean isOpenOrClose){
		 isOpenDebug=isOpenOrClose;
	 }

	public static void e(String string) {
		// TODO Auto-generated method stub
		
		if(!isOpenDebug)return;
		
		StringBuilder  sb=new StringBuilder();
		sb.append("=======start=======\n");
		sb.append(string);
		
 		System.out.println(sb.toString());
		sb.append("=======end=======\n");

	}

}
