package com.async.http.utils;

import android.util.Log;

public class LogUtils {

	 private  static boolean   isOpenDebug=true;
	 private  static boolean   isOpenLogHeaders=false;

	 public static boolean    isDebug(){
		 
		 return isOpenDebug;
	 }
	 
	 public static void setDebug(boolean isOpenOrClose){
		 isOpenDebug=isOpenOrClose;
	 }

	public static boolean isOpenLogHeaders() {
		return isOpenLogHeaders;
	}

	public static void setIsOpenLogHeaders(boolean isOpenLogHeaders) {
		LogUtils.isOpenLogHeaders = isOpenLogHeaders;
	}

	public static void e(String string) {
		// TODO Auto-generated method stub

		if(!isOpenDebug)return;
		
		StringBuilder  sb=new StringBuilder();
		sb.append("=======start=======\n");
		sb.append(string);
		sb.append("\n");
		sb.append("=======end=======\n");
		Log.e("tag",sb.toString());

	}

}
