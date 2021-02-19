package com.jackyli.androidarchitecture.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * author : lijie
 * date : 2019/12/5 13:56
 * e-mail : jackyli706@gmail.com
 * description :
 */
public class EncryptUtil {
    private static final EncryptUtil ourInstance = new EncryptUtil();

    public static EncryptUtil getInstance() {
        return ourInstance;
    }

    private EncryptUtil() {
    }

    /**
     * MD5加密
     *
     * @param message 需要进行md5的字符串
     * @return
     */
    public static String md5(String message) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(message.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return message;
    }
}
