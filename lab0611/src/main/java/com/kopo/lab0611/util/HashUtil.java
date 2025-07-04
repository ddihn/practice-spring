package com.kopo.lab0611.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

	/**
	 * 입력 문자열을 SHA-512 해시로 변환한 16진수 문자열을 반환합니다.
	 *
	 * @param input 해시할 원본 문자열 (예: 비밀번호)
	 * @return SHA-512 해시의 16진수 표현
	 */
	public static String sha512(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] hashedBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
			return bytesToHex(hashedBytes);
		} catch (NoSuchAlgorithmException e) {
			// SHA-512 알고리즘이 지원되지 않는 경우, 런타임 예외로 래핑
			throw new RuntimeException("SHA-512 algorithm not available", e);
		}
	}

	// 바이트 배열을 16진수 문자열로 변환
	private static String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
}
