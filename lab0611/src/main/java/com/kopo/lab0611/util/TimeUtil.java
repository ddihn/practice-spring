package com.kopo.lab0611.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

	/**
	 * 현재 시스템의 날짜와 시간을 "yyyy-MM-dd HH:mm:ss" 포맷의 문자열로 반환합니다. 예: "2025-06-12
	 * 09:15:30"
	 */
	public static String getCurrentTimestamp() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return now.format(fmt);
	}

	/**
	 * 현재 시스템의 날짜·시간을 LocalDateTime 객체로 그대로 반환합니다.
	 */
	public static LocalDateTime getCurrentDateTime() {
		return LocalDateTime.now();
	}
}
