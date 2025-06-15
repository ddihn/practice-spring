package com.kopo.hw0611.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    /**
     * �־��� ���ڿ��� SHA-512 �ؽ÷� ��ȯ�Ͽ� 16���� ���ڿ��� ��ȯ�մϴ�.
     *
     * @param input �ؽ��� �� ���ڿ�
     * @return SHA-512 �ؽð�(16���� ���ڿ�)
     */
    public static String sha512(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(input.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b)); // �� ����Ʈ�� 2�ڸ� 16������ ��ȯ
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 �˰����� ã�� �� �����ϴ�.", e);
        }
    }
}
