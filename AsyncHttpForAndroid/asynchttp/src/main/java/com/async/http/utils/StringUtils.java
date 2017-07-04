package com.async.http.utils;

public class StringUtils {

	/**
	 * Description:判断字段空null <br>
	 *
	 * @param s
	 * @return boolean
	 */
	public static boolean isNull(String s) {
		if (s == null || "".equals(s.trim())) {
			return true;
		}

		return false;
	}


}
