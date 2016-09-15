package com.async.http.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	public static String getNowTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}
}
