package com.async.http.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	public static String getNowTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
		return df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
	}
}
