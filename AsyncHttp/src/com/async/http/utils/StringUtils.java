package com.async.http.utils;

public class StringUtils {

	/**
	 * Description:ÅÐ¶Ï×Ö¶Î¿Õnull <br>
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
