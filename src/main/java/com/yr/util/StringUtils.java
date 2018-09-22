package com.yr.util;

public class StringUtils {
	public static boolean isNull(String str) {
		if (str.equals("") || str.equals(null)) {
			return true;
		}
		return false;
	}

	public static boolean isNull(Integer str) {
		if (str.equals("") || str.equals(null)) {
			return true;
		}
		return false;
	}

	public static boolean isNull(Double str) {
		if (str.equals("") || str.equals(null)) {
			return true;
		}
		return false;
	}
}
