package com.kopo.hw0611.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    /**
     * 주어진 문자열을 SHA-512 해시로 변환하여 16진수 문자열로 반환합니다.
     *
     * @param input 해싱할 평문 문자열
     * @return SHA-512 해시값(16진수 문자열)
     */
    public static String sha512(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(input.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b)); // 각 바이트를 2자리 16진수로 변환
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 알고리즘을 찾을 수 없습니다.", e);
        }
    }
}
